package lu.cgi.d4g.house.information.services;

import lu.cgi.d4g.house.information.entities.HomeEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.BadRequestException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.StreamSupport;

@ApplicationScoped
public class HomeService {

    @Inject
    EntityManager entityManager;

    public void save(@Valid HomeEntity homeEntity) {
        entityManager.persist(homeEntity);
        entityManager.flush();
    }

    public List<HomeEntity> findAllById(String id) {
        return entityManager.createQuery("SELECT ho FROM housing ho WHERE ho.home_id = :id", HomeEntity.class)
            .setParameter("id", id)
            .getResultList();
    }

    @Transactional
    public void importCsvData(String csv) {
        importCsvData(csv, record -> {
            HomeEntity home = new HomeEntity();
            home.setLabel(record.get("Foyer"));
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
