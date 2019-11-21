package lu.cgi.d4g.house.information.resources;

import lu.cgi.d4g.house.information.entities.TenantEntity;
import lu.cgi.d4g.house.information.services.TenantService;

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

@Path("/tenant")
public class TenantResource {

    @Inject
    public TenantService tenantService;

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTenant(@Valid TenantEntity tenantEntity) {
        tenantService.save(tenantEntity);
    }

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public List<TenantEntity> findAll(@Context SecurityContext securityContext) {
        return tenantService.findAllByUser(securityContext.getUserPrincipal().getName());
    }

    @GET
    @Path("/findAllBack")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<TenantEntity> findAllBack() {
        return tenantService.findAll();
    }
}
