package lu.cgi.d4g.house.information.resources;

import lu.cgi.d4g.house.information.entities.HomeEntity;
import lu.cgi.d4g.house.information.services.HomeService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
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
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public List<HomeEntity> find(@Context SecurityContext securityContext) {
        return homeService.findByUser(securityContext.getUserPrincipal().getName());
    }

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<HomeEntity> findAll() {
        return homeService.findAll();
    }

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public void addHome(@Valid HomeEntity homeEntity) {
        homeService.save(homeEntity);
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
