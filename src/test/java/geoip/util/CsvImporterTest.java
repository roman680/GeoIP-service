package geoip.util;

import geoip.model.IpLocation;
import geoip.repository.IpLocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CsvImporterTest {

    @InjectMocks
    private CsvImporter csvImporter;

    @Mock
    private IpLocationRepository ipLocationRepository;

    @Test
    public void importCSV_Ok() {
        String filePath = "src/test/resources/test.csv";
        csvImporter.importCsv(filePath);

        verify(ipLocationRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void importCSV_nonExistingFile_NotOk() {
        String nonExistingFilePath = "non_existing_file.csv";

        assertThrows(RuntimeException.class, () -> {
            csvImporter.importCsv(nonExistingFilePath);
        });
    }
}
