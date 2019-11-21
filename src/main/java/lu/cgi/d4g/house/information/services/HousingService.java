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

    public List<HousingEntity> findAllByUser(String id) {
        return entityManager.createQuery("SELECT ho FROM HousingEntity ho INNER JOIN UserEntity u ON u.home = ho.home WHERE u.userId = :user", HousingEntity.class)
            .setParameter("user", id)
            .getResultList();
    }

    public List<HousingEntity> findAll() {
        return entityManager.createQuery("SELECT ho FROM HousingEntity ho", HousingEntity.class)
            .getResultList();
    }

}
