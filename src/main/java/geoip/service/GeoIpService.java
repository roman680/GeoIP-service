package geoip.service;

import geoip.model.IpLocation;
import geoip.repository.IpLocationRepository;
import org.springframework.stereotype.Service;

@Service
public class GeoIpService {
    private final IpLocationRepository ipLocationRepository;

    public GeoIpService(IpLocationRepository ipLocationRepository) {
        this.ipLocationRepository = ipLocationRepository;
    }

    public IpLocation findIPLocation(String ip) {
        long ipNumber = convertIpToNumberNumber(ip);
        IpLocation location = ipLocationRepository.findIPLocationInRange(ipNumber);
        return location;
    }

    private long convertIpToNumberNumber(String ip) {
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
