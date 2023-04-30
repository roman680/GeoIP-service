package geoip.config;

import geoip.util.CsvImporter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
@Profile("import")
public class DataImportConfiguration {
    private final CsvImporter csvImporter;

    public DataImportConfiguration(CsvImporter csvImporter) {
        this.csvImporter = csvImporter;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String csvFilePath = "src/main/resources/data.CSV";
        csvImporter.importCsv(csvFilePath);
    }
}
