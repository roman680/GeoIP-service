package geoip.config;

import geoip.util.CsvImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
@Profile("import")
public class DataImportConfiguration {

    @Autowired
    private CsvImporter csvImporter;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String csvFilePath = "PATH_TO_CSV_FILE";
        csvImporter.importCsv(csvFilePath);
    }
}
