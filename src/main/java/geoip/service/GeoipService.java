package geoip.service;

import geoip.model.IPlocation;
import geoip.repository.IplocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoipService {

    @Autowired
    private IplocationRepository ipLocationRepository;

    public IPlocation findipLocation(String ip) {
        long ipNumber = convertIptoNumber(ip);
        return ipLocationRepository.findipLocationInrange(ipNumber);
    }

    private long convertIptoNumber(String ip) {
        String[] octets = ip.split("\\.");
        long result = 0;
        for (int i = 0; i < octets.length; i++) {
            int power = 3 - i;
            long octetValue = Long.parseLong(octets[i]) * (long) Math.pow(256, power);
            result += octetValue;
        }
        return result;
    }
}
