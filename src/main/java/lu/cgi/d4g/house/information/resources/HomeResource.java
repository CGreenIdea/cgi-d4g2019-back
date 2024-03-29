package lu.cgi.d4g.house.information.resources;

import lu.cgi.d4g.house.information.entities.HomeEntity;
import lu.cgi.d4g.house.information.services.HomeService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/home")
public class HomeResource {

    @Inject
    public HomeService homeService;

    @GET
    @Path("/mine")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public List<HomeEntity> find(@Context SecurityContext securityContext) {
        return homeService.findAllByUser(securityContext.getUserPrincipal().getName());
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<HomeEntity> findAll() {
        return homeService.findAll();
    }

    @PUT
    @Path("/import")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("admin")
    public Response importData(String csv) {
        homeService.importCsvData(csv);
        return Response.ok("Données importées").build();
    }
}
