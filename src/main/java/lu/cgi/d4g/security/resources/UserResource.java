package lu.cgi.d4g.security.resources;

import lu.cgi.d4g.services.MailerService;
import lu.cgi.d4g.security.dto.UserBean;
import lu.cgi.d4g.security.services.UserService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        String uuid = UUID.randomUUID().toString();
        userService.createUser(user, uuid);
        mailerService.sendVerification(user.getUsername(), uuid);

        return Response.accepted().build();
    }

}
