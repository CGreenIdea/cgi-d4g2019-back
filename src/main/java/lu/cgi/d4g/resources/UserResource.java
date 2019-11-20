package lu.cgi.d4g.resources;

import lu.cgi.d4g.entities.UserEntity;
import lu.cgi.d4g.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {

    @Inject
    public UserService userService;

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUsers(@Valid UserEntity userEntity) {
        userService.save(userEntity);
    }
}
