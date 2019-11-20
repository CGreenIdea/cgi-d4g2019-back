package lu.cgi.d4g.services;

import lu.cgi.d4g.entities.UsersEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

@ApplicationScoped
public class UsersService {

    @Inject
    private EntityManager entityManager;

    public void save(@Valid UsersEntity usersEntity) {
        entityManager.persist(usersEntity);
        entityManager.flush();
    }
}
