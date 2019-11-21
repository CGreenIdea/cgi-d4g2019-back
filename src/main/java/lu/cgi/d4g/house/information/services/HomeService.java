package lu.cgi.d4g.house.information.services;

import lu.cgi.d4g.house.information.entities.HomeEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
public class HomeService {

    @Inject
    EntityManager entityManager;

    public void save(@Valid HomeEntity homeEntity) {
        entityManager.persist(homeEntity);
        entityManager.flush();
    }

    public List<HomeEntity> findAll() {
        return entityManager.createQuery("SELECT h FROM home h order by h.label", HomeEntity.class)
            .getResultList();
    }

}
