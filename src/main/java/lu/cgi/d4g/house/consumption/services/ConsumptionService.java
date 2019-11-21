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

    public List<ConsumptionEntity> findAllByUser(String userId) {
        return entityManager.createQuery("SELECT c FROM ConsumptionEntity c INNER JOIN UserEntity u ON u.home = c.home WHERE u.userId = :user ORDER BY c.readingDate", ConsumptionEntity.class)
            .setParameter("user", userId)
            .getResultList();
    }

    public List<ConsumptionEntity> findAll() {
        return entityManager.createQuery("SELECT c FROM ConsumptionEntity c ORDER BY c.readingDate", ConsumptionEntity.class)
            .getResultList();
    }

    public List<ConsumptionEntity> getRangeConsumptionByUser(String userId, String dateStart, String dateEnd) {
        return entityManager.createQuery("SELECT c FROM ConsumptionEntity c INNER JOIN UserEntity u ON u.home = c.home WHERE u.userId = :user AND c.readingDate BETWEEN :dateStart AND :dateEnd ORDER BY c.readingDate", ConsumptionEntity.class)
            .setParameter("user", userId)
            .setParameter("dateStart", dateStart)
            .setParameter("dateEnd", dateEnd)
            .getResultList();
    }
}
