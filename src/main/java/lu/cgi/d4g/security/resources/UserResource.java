package lu.cgi.d4g.security.resources;

import lu.cgi.d4g.commons.services.MailerService;
import lu.cgi.d4g.house.information.dto.InformationBean;
import lu.cgi.d4g.house.information.entities.HomeEntity;
import lu.cgi.d4g.house.information.services.HomeService;
import lu.cgi.d4g.house.information.services.LandlordService;
import lu.cgi.d4g.house.information.services.TenantService;
import lu.cgi.d4g.security.dto.ResetBean;
import lu.cgi.d4g.security.dto.UserBean;
import lu.cgi.d4g.security.entities.UserEntity;
import lu.cgi.d4g.security.services.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.time.LocalDate;
import java.util.UUID;

@Path("/user")
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    LandlordService landlordService;

    @Inject
    TenantService tenantService;

    @Inject
    HomeService homeService;

    @Inject
    MailerService mailerService;

    @POST
    @Path("/register")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(InformationBean information) {
        UserBean user = information.getUser();

        if (user.getPassword().equals(user.getPasswordConfirm())) {
            String token = UUID.randomUUID().toString();
            HomeEntity home = homeService.createHome(information.getHome());
            userService.createUser(user, home, token);
            tenantService.createTenant(information.getTenant(), home);
            landlordService.createLandlord(information.getLandlord(), home);

            return mailerService.sendVerification(user.getUsername(), token).toCompletableFuture().join();
        } else {
            return Response.status(403).build();
        }
    }

    @POST
    @Path("/reset")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resetPassword(@Context SecurityContext securityContext) {
        String userName = securityContext.getUserPrincipal().getName();
        UserEntity user = userService.findByUserId(userName);
        String token = UUID.randomUUID().toString();
        user.setExpiryReset(LocalDate.now().plusDays(1));
        user.setResetToken(token);
        userService.update(user);

        return mailerService.sendReset(userName, token).toCompletableFuture().join();
    }

    @POST
    @Path("/change-password")
    public Response changePassword(ResetBean resetBean) {
        UserEntity user = userService.findByResetToken(resetBean.getToken());

        if (user != null && user.getExpiryReset() != null && !user.getExpiryReset().isAfter(LocalDate.now()) && resetBean.getPassword().equals(resetBean.getPasswordConfirm())) {
            user.setPassword(resetBean.getPassword());
            user.setExpiryReset(null);
            user.setResetToken(null);
            userService.update(user);

            return Response.status(200).build();
        }

        return Response.status(403).build();
    }

    @GET
    @Path("/registration/{token}")
    public Response registerUser(@PathParam("token") String token) {
        UserEntity user = userService.findByRegistrationToken(token);

        if (user != null && !user.isActive() && !user.getExpiryRegistration().isAfter(LocalDate.now())) {
            user.setRegistrationToken(null);
            user.setActive(true);
            user.setExpiryRegistration(null);
            userService.update(user);

            return Response.status(200).build();
        }

        return Response.status(403).build();
    }

    @PUT
    @Path("/import")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("admin")
    public Response importData(String csv) {
        userService.importCsvData(csv);
        return Response.ok("Données importées").build();
    }
}
