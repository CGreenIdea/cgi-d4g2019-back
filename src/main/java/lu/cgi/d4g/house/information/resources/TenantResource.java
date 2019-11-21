package lu.cgi.d4g.house.information.resources;

import lu.cgi.d4g.house.information.entities.TenantEntity;
import lu.cgi.d4g.house.information.services.TenantService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/tenant")
public class TenantResource {

    @Inject
    public TenantService tenantService;

    @GET
    @Path("/mine")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public List<TenantEntity> findAll(@Context SecurityContext securityContext) {
        return tenantService.findAllByUser(securityContext.getUserPrincipal().getName());
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<TenantEntity> findAllBack() {
        return tenantService.findAll();
    }

    @PUT
    @Path("/import")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("admin")
    public Response importData(String csv) {
        tenantService.importCsvData(csv);
        return Response.ok("Données importées").build();
    }
}
