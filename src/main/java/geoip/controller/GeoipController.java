package geoip.controller;

import geoip.model.IPlocation;
import geoip.service.GeoipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geoip")
public class GeoipController {

    @Autowired
    private GeoipService geoipService;

    @GetMapping("/{ip}")
    public IPlocation getIpLocation(@PathVariable("ip") String ip) {
        return geoipService.findipLocation(ip);
    }
}
