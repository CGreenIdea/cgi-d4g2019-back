package lu.cgi.d4g.house.information.services;

import lu.cgi.d4g.house.information.entities.LandlordEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
public class LandlordService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void save(@Valid LandlordEntity landlordEntity) {
        entityManager.persist(landlordEntity);
        entityManager.flush();
    }

    public List<LandlordEntity> findAllByUser(String id) {
        return entityManager.createQuery("SELECT l FROM LandlordEntity l INNER JOIN UserEntity u ON u.home = l.home_id WHERE u.userId = :user", LandlordEntity.class)
            .setParameter("user", id)
            .getResultList();
    }

    public List<LandlordEntity> findAll() {
        return entityManager.createQuery("SELECT l FROM LandlordEntity l", LandlordEntity.class)
            .getResultList();
    }

}
