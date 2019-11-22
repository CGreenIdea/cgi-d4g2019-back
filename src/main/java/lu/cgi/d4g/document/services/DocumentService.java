package lu.cgi.d4g.document.services;

import lu.cgi.d4g.document.entities.DocumentEntity;
import lu.cgi.d4g.house.information.entities.HomeEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.time.Instant;
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

    public DocumentEntity findByIdAndUsername(long docId, String userId) {
        try {
            return entityManager
                .createQuery("SELECT d FROM DocumentEntity d INNER JOIN UserEntity u ON d = u.home WHERE u.userId = :user AND d.id = :id", DocumentEntity.class)
                .setParameter("id", docId)
                .setParameter("user", userId)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void createDocument(HomeEntity home, String originalName, String localName, String title) {
        final DocumentEntity doc = new DocumentEntity();
        doc.setHome(home);
        doc.setTitle(title);
        doc.setFilename(originalName);
        doc.setLocalName(localName);
        doc.setCreationDate(Instant.now());

        entityManager.persist(doc);
    }
}
