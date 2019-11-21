package lu.cgi.d4g.security.resources;

import lu.cgi.d4g.security.dto.UserBean;
import lu.cgi.d4g.security.entities.UserEntity;
import lu.cgi.d4g.security.services.UserService;
import lu.cgi.d4g.commons.services.MailerService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

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
    public CompletionStage<Response> registerUser(UserBean user) {
        String uuid = UUID.randomUUID().toString();
        userService.createUser(user, uuid);

        return mailerService.sendVerification(user.getUsername(), uuid);
    }

    @GET
    @Path("/registration/{token}")
    public Response registerUser(@PathParam("token") String token) {
        UserEntity user = userService.findByRegistrationValidation(token);

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
