package geoip.util;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import geoip.model.IPlocation;
import geoip.repository.IplocationRepository;
import java.io.FileReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CsvImporter {

    private static final Logger logger = LoggerFactory.getLogger(CsvImporter.class);
    private final IplocationRepository ipLocationRepository;

    public CsvImporter(IplocationRepository ipLocationRepository) {
        this.ipLocationRepository = ipLocationRepository;
    }

    public void importCsv(String filePath) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(',')
                        .withQuoteChar('"')
                        .build())
                .build()) {

            logger.info("Reading CSV file: {}", filePath);
            reader.skip(1);

            String[] fields;
            while ((fields = reader.readNext()) != null) {
                IPlocation ipLocation = new IPlocation();
                ipLocation.setIpAddressFrom(Long.valueOf(fields[0]));
                ipLocation.setIpAddressTo(Long.valueOf(fields[1]));
                ipLocation.setCountryCode(fields[2]);
                ipLocation.setCountryName(fields[3]);
                ipLocation.setRegionName(fields[4]);
                ipLocation.setCityName(fields[5]);
                ipLocation.setLatitude(Double.parseDouble(fields[6]));
                ipLocation.setLongitude(Double.parseDouble(fields[7]));

                ipLocationRepository.save(ipLocation);
            }

            logger.info("Data imported successfully.");
        } catch (IOException | CsvException e) {
            logger.error("Error importing data: {}", e.getMessage());
            throw new RuntimeException("Can't insert data from csv file: " + filePath);
        }
    }
}
