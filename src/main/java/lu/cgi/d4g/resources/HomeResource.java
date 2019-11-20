package lu.cgi.d4g.resources;

import lu.cgi.d4g.entities.HomeEntity;
import lu.cgi.d4g.services.HomeService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/home")
public class HomeResource {

    @Inject
    public HomeService homeService;

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addHome(@Valid HomeEntity homeEntity) {
        homeService.save(homeEntity);
    }
}
