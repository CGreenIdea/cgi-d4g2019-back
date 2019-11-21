package lu.cgi.d4g.house.consumption.services;

import lu.cgi.d4g.house.consumption.entities.ConsumptionEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
public class ConsumptionService {

    @Inject
    EntityManager entityManager;

    public void save(@Valid ConsumptionEntity consumptionEntity) {
        entityManager.persist(consumptionEntity);
        entityManager.flush();
    }

    public List<ConsumptionEntity> findAllById(String id) {
        return entityManager.createQuery("SELECT c FROM consumption c WHERE c.home_id = :id order by c.readingDate", ConsumptionEntity.class)
            .setParameter("id", id)
            .getResultList();
    }

    public List<ConsumptionEntity> getRangeConsumptionById(String id, String dateStart, String dateEnd) {
        return entityManager.createQuery("SELECT c FROM consumption c WHERE c.home_id = :id AND c.readingDate BETWEEN :dateStart AND :dateEnd", ConsumptionEntity.class)
            .setParameter("id", id)
            .setParameter("dateStart", dateStart)
            .setParameter("dateEnd", dateEnd)
            .getResultList();
    }
}
