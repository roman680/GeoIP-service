package geoip.service;

import geoip.model.IpLocation;
import geoip.repository.IpLocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GeoipServiceTest {

    @InjectMocks
    private GeoIpService geoIPService;

    @Mock
    private IpLocationRepository ipLocationRepository;

    private IpLocation testIPlocation;

    @BeforeEach
    public void setup() {
        testIPlocation = new IpLocation();
        testIPlocation.setCityName("Los Angeles");
        testIPlocation.setCountryCode("US");
        testIPlocation.setCountryName("United States");
        testIPlocation.setIpAddressFrom(3232235520L);
        testIPlocation.setIpAddressTo(3232301055L);
        testIPlocation.setLatitude(34.05223);
        testIPlocation.setLongitude(-118.24368);
        testIPlocation.setRegionName("California");
    }

    @Test
    public void findIPLocation_Ok() {
        String ipAddress = "192.168.1.1";
        when(ipLocationRepository.findIPLocationInRange(anyLong())).thenReturn(testIPlocation);

        IpLocation result = geoIPService.findIPLocation(ipAddress);

        assertEquals(testIPlocation, result);
    }

    @Test
    public void findIPLocation_invalidIp_NotOk() {
        String invalidIpAddress = "999.999.999.999";

        IpLocation result = geoIPService.findIPLocation(invalidIpAddress);

        assertNull(result);
    }
}
