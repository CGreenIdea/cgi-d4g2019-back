package lu.cgi.d4g.security.resources;

import lu.cgi.d4g.security.dto.UserBean;
import lu.cgi.d4g.security.services.UserService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {

    @Inject
    UserService userService;

    @POST
    @Path("/register")
    @PermitAll
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(UserBean user) {
        userService.createUser(user);
        return Response.accepted().build();
    }

}
