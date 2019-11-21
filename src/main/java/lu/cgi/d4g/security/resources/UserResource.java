package lu.cgi.d4g.security.resources;

import lu.cgi.d4g.security.dto.UserBean;
import lu.cgi.d4g.security.entities.UserEntity;
import lu.cgi.d4g.security.services.UserService;
import lu.cgi.d4g.services.MailerService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    MailerService mailerService;

    @POST
    @Path("/register")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(UserBean user) {
        if (user.getPassword().equals(user.getPasswordConfirm())) {
            String token = UUID.randomUUID().toString();
            userService.createUser(user, token);

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

    @GET
    @Path("/reset/{token}")
    public Response changePassword(@PathParam("token") String token) {
        UserEntity user = userService.findByResetToken(token);

        if (user != null && user.getExpiryReset() != null && !user.getExpiryReset().isAfter(LocalDate.now())) {
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

}
