package lu.cgi.d4g.house.information.resources;

import lu.cgi.d4g.house.information.entities.LandlordEntity;
import lu.cgi.d4g.house.information.services.LandlordService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/landlord")
public class LandlordResource {

    @Inject
    public LandlordService landlordService;

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public List<LandlordEntity> findAll(@Context SecurityContext securityContext) {
        return landlordService.findAllByUser(securityContext.getUserPrincipal().getName());
    }

    @GET
    @Path("/findAllBack")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<LandlordEntity> findAllBack() {
        return landlordService.findAll();
    }
}
