package lu.cgi.d4g.house.information.services;

import lu.cgi.d4g.house.information.dto.TenantBean;
import lu.cgi.d4g.house.information.entities.HomeEntity;
import lu.cgi.d4g.house.information.entities.TenantEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class TenantService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void createTenant(TenantBean tenantBean, HomeEntity home) {
        TenantEntity tenant = new TenantEntity();

        tenant.setHome(home);
        tenant.setFirstName(tenantBean.getFirstName());
        tenant.setLastName(tenantBean.getLastName());

        entityManager.persist(tenant);
        entityManager.flush();
    }

    public List<TenantEntity> findAllByUser(String id) {
        return entityManager.createQuery("SELECT t FROM TenantEntity t INNER JOIN UserEntity u ON u.home = t.home WHERE u.userId = :user", TenantEntity.class)
            .setParameter("user", id)
            .getResultList();
    }

    public List<TenantEntity> findAll() {
        return entityManager.createQuery("SELECT t FROM TenantEntity t", TenantEntity.class)
            .getResultList();
    }

}
