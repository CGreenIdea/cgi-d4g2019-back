package lu.cgi.d4g.resources;

import lu.cgi.d4g.entities.HousingEntity;
import lu.cgi.d4g.services.HousingService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
