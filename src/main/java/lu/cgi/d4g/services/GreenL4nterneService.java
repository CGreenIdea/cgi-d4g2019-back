package lu.cgi.d4g.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class GreenL4nterneService {

    @Inject
    public EntityManager entityManager;
}
