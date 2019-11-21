package lu.cgi.d4g.commons.services;

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
import java.util.function.Function;
import java.util.stream.StreamSupport;

/**
 * A service to help importing CSV data.
 *
 * @author Cyrille Chopelet
 */
@ApplicationScoped
public class CsvImportService {

    @Inject
    EntityManager entityManager;

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
