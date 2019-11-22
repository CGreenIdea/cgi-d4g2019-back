package lu.cgi.d4g;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/document")
public class DocumentResource {

    @Inject
    public DocumentService documentService;

    @GET
    @Path("/mine")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public List<DocumentEntity> find(@Context SecurityContext securityContext) {
        return documentService.findByUser(securityContext.getUserPrincipal().getName());
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<DocumentEntity> findAll() {
        return documentService.findAll();
    }

}
