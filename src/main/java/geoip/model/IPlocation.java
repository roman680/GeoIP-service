package geoip.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "iplocation")
public class IPlocation {

    @Id
    @Column(name = "ip_address_from")
    private Long ipAddressFrom;

    @Column(name = "ip_address_to")
    private Long ipAddressTo;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    public Long getIpAddressFrom() {
        return ipAddressFrom;
    }

    public void setIpAddressFrom(Long ipAddressFrom) {
        this.ipAddressFrom = ipAddressFrom;
    }

    public Long getIpAddressTo() {
        return ipAddressTo;
    }

    public void setIpAddressTo(Long ipAddressTo) {
        this.ipAddressTo = ipAddressTo;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}


