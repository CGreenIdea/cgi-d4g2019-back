package lu.cgi.d4g.services;

import lu.cgi.d4g.entities.TenantEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

@ApplicationScoped
public class TenantService {

    @Inject
    EntityManager entityManager;

    public void save(@Valid TenantEntity tenantEntity) {
        entityManager.persist(tenantEntity);
        entityManager.flush();
    }
}
