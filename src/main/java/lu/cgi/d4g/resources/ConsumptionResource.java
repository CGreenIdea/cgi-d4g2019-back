package lu.cgi.d4g.resources;

import lu.cgi.d4g.bean.ConsumptionBean;
import lu.cgi.d4g.entities.ConsumptionEntity;
import lu.cgi.d4g.services.ConsumptionService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    @Path("/getRangeConsumptionByUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ConsumptionEntity> getRangeConsumptionByUser(ConsumptionBean consumptionBean) {
        String user = ""; // TODO userId
        return consumptionService.getRangeConsumptionByUser(user, consumptionBean.getDateStart(), consumptionBean.getDateEnd());
    }
}
