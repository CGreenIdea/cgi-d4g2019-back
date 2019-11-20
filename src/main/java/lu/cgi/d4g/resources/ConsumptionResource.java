package lu.cgi.d4g.resources;

import lu.cgi.d4g.bean.ConsumptionBean;
import lu.cgi.d4g.entities.ConsumptionEntity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/consumption")
public class ConsumptionResource {

    @Inject
    public EntityManager entityManager;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ConsumptionEntity> getRangeConsumptionByUser(ConsumptionBean consumptionBean) {
        return entityManager.createQuery("SELECT c FROM consumption c WHERE c.id = :id AND c.readingDate BETWEEN :dateStart AND :dateEnd", ConsumptionEntity.class)
            .setParameter("id", "0") // TODO
            .setParameter("dateStart", consumptionBean.getDateStart())
            .setParameter("dateEnd", consumptionBean.getDateEnd())
            .getResultList();
    }
}
