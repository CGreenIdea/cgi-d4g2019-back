package lu.cgi.d4g.document.services;

import lu.cgi.d4g.document.entities.DocumentEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class DocumentService {

    @Inject
    EntityManager entityManager;

    public List<DocumentEntity> findByUser(String userId) {
        return entityManager
            .createQuery("SELECT d FROM DocumentEntity d INNER JOIN UserEntity u ON d = u.home WHERE u.userId = :user", DocumentEntity.class)
            .setParameter("user", userId)
            .getResultList();
    }

    public List<DocumentEntity> findAll() {
        return entityManager
            .createQuery("SELECT d FROM DocumentEntity d", DocumentEntity.class)
            .getResultList();
    }

}
