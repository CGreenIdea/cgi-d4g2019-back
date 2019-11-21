package lu.cgi.d4g.house.information.resources;

import lu.cgi.d4g.house.information.entities.HousingEntity;
import lu.cgi.d4g.house.information.services.HousingService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/housing")
public class HousingResource {

    @Inject
    public HousingService housingService;

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addHousing(@Valid HousingEntity housingEntity) {
        housingService.save(housingEntity);
    }

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HousingEntity> findAll() {
        String id = ""; // TODO id
        return housingService.findAllById(id);
    }
}
