package lu.cgi.d4g.services;

import lu.cgi.d4g.entities.LandlordEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

@ApplicationScoped
public class LandlordService {

    @Inject
    private EntityManager entityManager;

    public void save(@Valid LandlordEntity landlordEntity) {
        entityManager.persist(landlordEntity);
        entityManager.flush();
    }
}
