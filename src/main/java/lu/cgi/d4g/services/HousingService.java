package lu.cgi.d4g.services;

import lu.cgi.d4g.entities.HousingEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

@ApplicationScoped
public class HousingService {

    @Inject
    EntityManager entityManager;

    public void save(@Valid HousingEntity housingEntity) {
        entityManager.persist(housingEntity);
        entityManager.flush();
    }
}
