package lu.cgi.d4g.services;

import lu.cgi.d4g.entities.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

@ApplicationScoped
public class UserService {

    @Inject
    EntityManager entityManager;

    public void save(@Valid UserEntity userEntity) {
        entityManager.persist(userEntity);
        entityManager.flush();
    }
}
