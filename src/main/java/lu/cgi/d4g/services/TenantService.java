package lu.cgi.d4g.services;

import lu.cgi.d4g.entities.TenantEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

@ApplicationScoped
public class TenantService {

    @Inject
    EntityManager entityManager;

    public void save(@Valid TenantEntity tenantEntity) {
        entityManager.persist(tenantEntity);
        entityManager.flush();
    }

    public List<TenantEntity> findAllById(String id) {
        return entityManager.createQuery("SELECT t FROM tenant t WHERE t.home_id = :id", TenantEntity.class)
            .setParameter("id", id)
            .getResultList();
    }

}
