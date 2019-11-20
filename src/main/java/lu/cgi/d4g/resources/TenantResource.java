package lu.cgi.d4g.resources;

import lu.cgi.d4g.entities.TenantEntity;
import lu.cgi.d4g.services.TenantService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
