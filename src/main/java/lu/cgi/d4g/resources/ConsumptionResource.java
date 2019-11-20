package lu.cgi.d4g.resources;

import lu.cgi.d4g.bean.ConsumptionBean;
import lu.cgi.d4g.entities.ConsumptionEntity;
import lu.cgi.d4g.services.ConsumptionService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/consumption")
public class ConsumptionResource {

    @Inject
    public ConsumptionService consumptionService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ConsumptionEntity> getRangeConsumptionByUser(ConsumptionBean consumptionBean) {
        return consumptionService.getRangeConsumptionByUser("", consumptionBean.getDateStart(), consumptionBean.getDateEnd());  // TODO userId
    }
}
