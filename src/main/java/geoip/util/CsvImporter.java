package geoip.util;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import geoip.model.IpLocation;
import geoip.repository.IpLocationRepository;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CsvImporter {
    private static final int BATCH_SIZE = 1000;
    private static final int NUM_THREADS = 4;

    private final IpLocationRepository ipLocationRepository;

    public CsvImporter(IpLocationRepository ipLocationRepository) {
        this.ipLocationRepository = ipLocationRepository;
    }

    @Transactional
    public void importCsv(String filePath) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(',')
                        .withQuoteChar('"')
                        .build())
                .build()) {

            reader.skip(1);

            ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
            List<IpLocation> ipLocations = new ArrayList<>();
            String[] fields;
            while ((fields = reader.readNext()) != null) {
                IpLocation ipLocation = new IpLocation();
                ipLocation.setIpAddressFrom(Long.valueOf(fields[0]));
                ipLocation.setIpAddressTo(Long.valueOf(fields[1]));
                ipLocation.setCountryCode(fields[2]);
                ipLocation.setCountryName(fields[3]);
                ipLocation.setRegionName(fields[4]);
                ipLocation.setCityName(fields[5]);
                ipLocation.setLatitude(Double.parseDouble(fields[6]));
                ipLocation.setLongitude(Double.parseDouble(fields[7]));

                ipLocations.add(ipLocation);

                if (ipLocations.size() >= BATCH_SIZE) {
                    List<IpLocation> batch = ipLocations;
                    ipLocations = new ArrayList<>();
                    executor.submit(() -> saveBatch(batch));
                }
            }

            if (!ipLocations.isEmpty()) {
                List<IpLocation> remainingBatch = ipLocations;
                executor.submit(() -> saveBatch(remainingBatch));
            }

            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

        } catch (IOException | CsvException | InterruptedException e) {
            throw new RuntimeException("Can't insert data from csv file: " + filePath);
        }
    }

    @Transactional
    void saveBatch(List<IpLocation> ipLocations) {
        ipLocationRepository.saveAll(ipLocations);
    }
}

