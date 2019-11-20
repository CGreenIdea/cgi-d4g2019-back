package lu.cgi.d4g.resources;

import lu.cgi.d4g.entities.LandlordEntity;
import lu.cgi.d4g.services.LandlordService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/landlord")
public class LandlordResource {

    @Inject
    public LandlordService landlordService;

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addLandlord(@Valid LandlordEntity landlordEntity) {
        landlordService.save(landlordEntity);
    }
}
