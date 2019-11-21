package lu.cgi.d4g.house.information.services;

import lu.cgi.d4g.commons.services.CsvImportService;
import lu.cgi.d4g.house.information.entities.HomeEntity;
import lu.cgi.d4g.house.information.entities.LandlordEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import java.util.List;

@ApplicationScoped
public class LandlordService {

    @Inject
    HomeService homeService;

    @Inject
    CsvImportService csvImportService;

    @Inject
    EntityManager entityManager;

    @Transactional
    public void save(@Valid LandlordEntity landlordEntity) {
        entityManager.persist(landlordEntity);
        entityManager.flush();
    }

    public List<LandlordEntity> findAllByUser(String id) {
        return entityManager.createQuery("SELECT l FROM LandlordEntity l INNER JOIN UserEntity u ON u.home = l.home WHERE u.userId = :user", LandlordEntity.class)
            .setParameter("user", id)
            .getResultList();
    }

    public List<LandlordEntity> findAll() {
        return entityManager.createQuery("SELECT l FROM LandlordEntity l", LandlordEntity.class)
            .getResultList();
    }

    @Transactional
    public void importCsvData(String csv) {
        csvImportService.importCsvData(csv, record -> {
            LandlordEntity landlord = new LandlordEntity();

            final String homeLabel = record.get("Foyer");
            if (homeLabel == null || "".equals(homeLabel.trim())) {
                throw new BadRequestException("Inconsistent data");
            }

            final HomeEntity home = homeService.findHomeByLabel(homeLabel);
            landlord.setHome(home);

            landlord.setFirstName(record.get("Prénom"));
            landlord.setLastName(record.get("Nom"));
            landlord.setCompany(record.get("Société"));
            landlord.setAddress(record.get("Adresse"));

            return landlord;
        });
    }
}
