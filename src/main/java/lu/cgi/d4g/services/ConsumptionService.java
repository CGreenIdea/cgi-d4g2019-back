package lu.cgi.d4g.services;

import lu.cgi.d4g.entities.ConsumptionEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ConsumptionService {
    @Inject
    private EntityManager entityManager;

    public List<ConsumptionEntity> getRangeConsumptionByUser(String userId, String dateStart, String dateEnd) {
        return entityManager.createQuery("SELECT c FROM consumption c WHERE c.id = :id AND c.readingDate BETWEEN :dateStart AND :dateEnd", ConsumptionEntity.class)
            .setParameter("id", userId)
            .setParameter("dateStart", dateStart)
            .setParameter("dateEnd", dateEnd)
            .getResultList();
    }
}