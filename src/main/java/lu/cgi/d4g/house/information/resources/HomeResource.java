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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/home")
public class HomeResource {

    @Inject
    public HomeService homeService;

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addHousing(@Valid HomeEntity homeEntity) {
        homeService.save(homeEntity);
    }

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HomeEntity> findAll() {
        String id = ""; // TODO id
        return homeService.findAllById(id);
    }

    @PUT
    @Path("/import")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("admin")
    private Response importData(String csv) {
homeService.importCsvData(csv);
        return Response.ok("Données importées").build();
    }
}
