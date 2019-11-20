package lu.cgi.d4g.resources;

import lu.cgi.d4g.entities.UsersEntity;
import lu.cgi.d4g.services.UsersService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UsersResource {

    @Inject
    public UsersService usersService;

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUsers(@Valid UsersEntity usersEntity) {
        usersService.save(usersEntity);
    }
}
