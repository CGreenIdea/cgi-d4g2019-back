package lu.cgi.d4g.house.information.resources;

import lu.cgi.d4g.house.information.entities.HousingEntity;
import lu.cgi.d4g.house.information.services.HousingService;

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
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/housing")
public class HousingResource {

    @Inject
    public HousingService housingService;

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public List<HousingEntity> findAll(@Context SecurityContext securityContext) {
        return housingService.findAllByUser(securityContext.getUserPrincipal().getName());
    }

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public void addHousing(@Valid HousingEntity housingEntity) {
        housingService.save(housingEntity);
    }

    @GET
    @Path("/findAllBack")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<HousingEntity> findAllBack() {
        return housingService.findAll();
    }
}
