package geoip.controller;

import geoip.model.IpLocation;
import geoip.model.IpLocationResponse;
import geoip.service.GeoIpService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GeoIpController.class)
public class GeoIpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeoIpService geoIpService;

    @Test
    public void getIpLocation_ValidIp_ReturnsIpLocationResponse() throws Exception {
        IpLocation ipLocation = new IpLocation();
        ipLocation.setCountryName("United States");
        ipLocation.setRegionName("California");
        ipLocation.setCityName("Los Angeles");

        when(geoIpService.findIPLocation(anyString())).thenReturn(ipLocation);

        mockMvc.perform(get("/geoip/8.8.8.8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryName").value("United States"))
                .andExpect(jsonPath("$.regionName").value("California"))
                .andExpect(jsonPath("$.cityName").value("Los Angeles"));
    }


    @Test
    public void getIpLocation_InvalidIp_ReturnsNotFound() throws Exception {
        when(geoIpService.findIPLocation(anyString())).thenReturn(null);

        mockMvc.perform(get("/geoip/invalid.ip"))
                .andExpect(status().isNotFound());
    }
}
