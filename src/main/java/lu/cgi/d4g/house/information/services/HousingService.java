package lu.cgi.d4g.house.information.services;

import lu.cgi.d4g.house.information.entities.HousingEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
public class HousingService {

    @Inject
    EntityManager entityManager;

    public void save(@Valid HousingEntity housingEntity) {
        entityManager.persist(housingEntity);
        entityManager.flush();
    }

    public List<HousingEntity> findAllById(String id) {
        return entityManager.createQuery("SELECT ho FROM housing ho WHERE ho.home_id = :id", HousingEntity.class)
            .setParameter("id", id)
            .getResultList();
    }

}
