package lu.cgi.d4g.house.information.services;

import lu.cgi.d4g.house.information.entities.LandlordEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
public class LandlordService {

    @Inject
    EntityManager entityManager;

    public void save(@Valid LandlordEntity landlordEntity) {
        entityManager.persist(landlordEntity);
        entityManager.flush();
    }

    public List<LandlordEntity> findAllById(String id) {
        return entityManager.createQuery("SELECT l FROM LandlordEntity l WHERE l.home_id = :id", LandlordEntity.class)
            .setParameter("id", id)
            .getResultList();
    }

}
