package geoip.controller;

import geoip.model.IpLocation;
import geoip.model.IpLocationResponse;
import geoip.service.GeoIpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geoip")
public class GeoIpController {
    private final GeoIpService geoipService;

    public GeoIpController(GeoIpService geoipService) {
        this.geoipService = geoipService;
    }

    @GetMapping("/{ip}")
    public ResponseEntity<IpLocationResponse> getIpLocation(@PathVariable("ip") String ip) {
        IpLocation ipLocation = geoipService.findIPLocation(ip);
        if (ipLocation != null) {
            IpLocationResponse response = IpLocationResponse.fromIPLocation(ipLocation, ip);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}
