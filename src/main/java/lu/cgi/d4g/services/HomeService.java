package lu.cgi.d4g.services;

import lu.cgi.d4g.entities.HomeEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

@ApplicationScoped
public class HomeService {

    @Inject
    private EntityManager entityManager;

    public void save(@Valid HomeEntity homeEntity) {
        entityManager.persist(homeEntity);
        entityManager.flush();
    }
}
