package lu.cgi.d4g.house.consumption.resources;

import lu.cgi.d4g.house.consumption.entities.ConsumptionEntity;
import lu.cgi.d4g.house.consumption.services.ConsumptionService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.time.LocalDate;
import java.util.List;

@Path("/consumption")
public class ConsumptionResource {

    @Inject
    public ConsumptionService consumptionService;

    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addConsumption(@Valid ConsumptionEntity consumptionEntity) {
        consumptionService.save(consumptionEntity);
    }

    @GET
    @Path("/range/{dateStart}/{dateEnd}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ConsumptionEntity> getRangeConsumption(
        @Context SecurityContext securityContext,
        @PathParam("dateStart") String dateStart,
        @PathParam("dateEnd") String dateEnd
    ) {
        return consumptionService.getRangeConsumptionByUser(
            securityContext.getUserPrincipal().getName(),
            LocalDate.parse(dateStart),
            LocalDate.parse(dateEnd)
        );
    }

    @GET
    @Path("/average")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public double getAverageConsumption(@Context SecurityContext securityContext) {
        return consumptionService.getAverageConsumptionByUser(securityContext.getUserPrincipal().getName());
    }

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    public List<ConsumptionEntity> findAll(@Context SecurityContext securityContext) {
        return consumptionService.findAllByUser(securityContext.getUserPrincipal().getName());
    }

    @GET
    @Path("/findAllBack")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConsumptionEntity> findAllBack() {
        return consumptionService.findAll();
    }

    @PUT
    @Path("/import")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("admin")
    public Response importData(String csv) {
        consumptionService.importCsvData(csv);
        return Response.ok("Données importées").build();
    }
}
