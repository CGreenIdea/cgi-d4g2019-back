package lu.cgi.d4g.house.consumption.services;

import lu.cgi.d4g.commons.services.CsvImportService;
import lu.cgi.d4g.house.consumption.entities.ConsumptionEntity;
import lu.cgi.d4g.house.information.entities.HomeEntity;
import lu.cgi.d4g.house.information.services.HomeService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ApplicationScoped
public class ConsumptionService {

    @Inject
    HomeService homeService;

    @Inject
    CsvImportService csvImportService;

    @Inject
    EntityManager entityManager;

    @Transactional
    public void save(@Valid ConsumptionEntity consumptionEntity) {
        entityManager.persist(consumptionEntity);
        entityManager.flush();
    }

    public List<ConsumptionEntity> findAllByUser(String userId) {
        return entityManager
            .createQuery("SELECT c FROM ConsumptionEntity c INNER JOIN UserEntity u ON u.home = c.home WHERE u.userId = :user ORDER BY c.readingDate", ConsumptionEntity.class)
            .setParameter("user", userId)
            .getResultList();
    }

    public List<ConsumptionEntity> findAll() {
        return entityManager
            .createQuery("SELECT c FROM ConsumptionEntity c ORDER BY c.readingDate", ConsumptionEntity.class)
            .getResultList();
    }

    public List<ConsumptionEntity> getRangeConsumptionByUser(String userId, LocalDate dateStart, LocalDate dateEnd) {
        return entityManager
            .createQuery("SELECT c FROM ConsumptionEntity c INNER JOIN UserEntity u ON u.home = c.home WHERE u.userId = :user AND c.readingDate BETWEEN :dateStart AND :dateEnd ORDER BY c.readingDate", ConsumptionEntity.class)
            .setParameter("user", userId)
            .setParameter("dateStart", dateStart)
            .setParameter("dateEnd", dateEnd)
            .getResultList();
    }

    @Transactional
    public void importCsvData(String csv) {
        csvImportService.importAndFlattenCsvData(csv, record -> {
            final String homeLabel = record.get("Foyer");
            if (homeLabel != null && !"".equals(homeLabel.trim())) {
                final HomeEntity home = homeService.findHomeByLabel(homeLabel);
                if (home != null) {
                    return record.toMap().entrySet().stream()
                        .skip(1L)
                        .map(e -> {
                            ConsumptionEntity dataPoint = new ConsumptionEntity();
                            dataPoint.setHome(home);
                            dataPoint.setReadingDate(LocalDate.parse(e.getKey(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                            dataPoint.setEnergy(Integer.parseInt(e.getValue()));
                            return dataPoint;
                        });
                }
            }
            return null;
        });
    }
}
