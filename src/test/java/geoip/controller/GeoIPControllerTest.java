package geoip.controller;

import geoip.model.IPlocation;
import geoip.service.GeoipService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GeoIPControllerTest {

    @InjectMocks
    private GeoipController controller;

    @Mock
    private GeoipService service;

    @Test
    public void getIpLocation_Ok() {
        IPlocation ipLocation = new IPlocation();
        ipLocation.setCityName("Mountain View");
        ipLocation.setCountryCode("US");
        ipLocation.setCountryName("United States");
        ipLocation.setIpAddressFrom(134744064L);
        ipLocation.setIpAddressTo(134744072L);
        ipLocation.setLatitude(37.405992);
        ipLocation.setLongitude(-122.078515);
        ipLocation.setRegionName("California");
        when(service.findipLocation("8.8.8.8")).thenReturn(ipLocation);

        IPlocation response = controller.getIpLocation("8.8.8.8");
        assertEquals(ipLocation, response);
    }

    @Test
    public void getIpLocation_NotOk() {
        IPlocation ipLocation = new IPlocation();
        when(service.findipLocation("INVALID_IP")).thenReturn(ipLocation);

        IPlocation response = controller.getIpLocation("INVALID_IP");
        assertEquals(ipLocation, response);
    }
}
