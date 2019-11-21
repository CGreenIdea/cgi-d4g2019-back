package lu.cgi.d4g.house.information.services;

import lu.cgi.d4g.commons.services.CsvImportService;
import lu.cgi.d4g.house.information.dto.HomeBean;
import lu.cgi.d4g.house.information.entities.HomeEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.StreamSupport;

@ApplicationScoped
public class HomeService {

    @Inject
    CsvImportService csvImportService;

    @Inject
    EntityManager entityManager;

    @Transactional
    public HomeEntity createHome(HomeBean homeBean) {
        HomeEntity home = new HomeEntity();

        home.setCity(homeBean.getCity());
        home.setConstructionYear(homeBean.getConstructionYear());
        home.setHeatSource(homeBean.getHeatSource());
        home.setLabel(homeBean.getLabel());
        home.setNbRooms(homeBean.getNbRooms());
        home.setStreet(homeBean.getStreet());
        home.setStreetNb(homeBean.getStreetNb());
        home.setSurface(homeBean.getSurface());
        home.setZipCode(homeBean.getZipCode());
        home.setType((short) (homeBean.getType() != 1 ? 0 : 1));

        entityManager.persist(home);
        entityManager.flush();

        return home;
    }

    public List<HomeEntity> findByUser(String userId) {
        return entityManager
            .createQuery("SELECT h FROM HomeEntity h INNER JOIN UserEntity u ON h = u.home WHERE u.userId = :user", HomeEntity.class)
            .setParameter("user", userId)
            .getResultList();
    }

    public List<HomeEntity> findAll() {
        return entityManager
            .createQuery("SELECT h FROM HomeEntity h", HomeEntity.class)
            .getResultList();
    }

    public HomeEntity findHomeByLabel(String label) {
        return entityManager
            .createQuery("SELECT h FROM HomeEntity h WHERE h.label = :label", HomeEntity.class)
            .setParameter("label", label)
            .getSingleResult();
    }

    @Transactional
    public void importCsvData(String csv) {
        csvImportService.importCsvData(csv, record -> {
            HomeEntity home = new HomeEntity();
            home.setLabel(record.get("Foyer"));
            home.setType(Short.parseShort(record.get("type")));
            home.setSurface(Integer.parseInt(record.get("Surface")));
            home.setNbRooms(Integer.parseInt(record.get("Pièces")));
            home.setHeatSource(record.get("Chauffage"));
            home.setConstructionYear(Integer.parseInt(record.get("Année de construction")));
            home.setStreetNb(record.get("n° de voie"));
            home.setStreet(record.get("Voie 1"));
            home.setZipCode(record.get("code postal"));
            home.setCity(record.get("ville"));
            return home;
        });
    }

    @Transactional
    public void importCsvData(String csv, Function<CSVRecord, HomeEntity> mapper) {
        try (
            CSVParser csvParser = new CSVParser(new StringReader(csv), CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(';'))
        ) {
            StreamSupport.stream(csvParser.spliterator(), false)
                .map(mapper)
                .forEach(item -> entityManager.persist(item));
        } catch (IOException e) {
            throw new BadRequestException("The CSV could not be parsed");
        }
    }
}
