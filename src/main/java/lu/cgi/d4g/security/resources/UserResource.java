package lu.cgi.d4g.security.resources;

import lu.cgi.d4g.security.dto.UserBean;
import lu.cgi.d4g.security.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserResource {

    @Inject
    UserService userService;

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserBean user) {
        userService.createUser(user);
        return Response.accepted().build();
    }
}
