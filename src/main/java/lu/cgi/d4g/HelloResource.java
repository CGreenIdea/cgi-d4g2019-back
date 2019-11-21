package lu.cgi.d4g;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/hello")
public class HelloResource {

    @GET
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello, anonymous";
    }

    @GET
    @Path("/me")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed({"user", "admin"})
    public String hello(@Context SecurityContext securityContext) {
        return "Hello, " + securityContext.getUserPrincipal().getName();
    }
}
