package geoip.model;

public class IpLocationResponse {
    private String canonicalIPv4Representation;
    private String cityName;
    private String countryCode;
    private String countryName;
    private String ipv4;
    private Double latitude;
    private Double longitude;
    private String regionName;

    public String getCanonicalIPv4Representation() {
        return canonicalIPv4Representation;
    }

    public void setCanonicalIPv4Representation(String canonicalIPv4Representation) {
        this.canonicalIPv4Representation = canonicalIPv4Representation;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public static IpLocationResponse fromIPLocation(IpLocation ipLocation,
                                                    String canonicalIPv4Representation) {
        IpLocationResponse response = new IpLocationResponse();
        response.setCanonicalIPv4Representation(canonicalIPv4Representation);
        response.setCityName(ipLocation.getCityName());
        response.setCountryCode(ipLocation.getCountryCode());
        response.setCountryName(ipLocation.getCountryName());
        response.setIpv4(String.valueOf(ipLocation.getIpAddressFrom()));
        response.setLatitude(ipLocation.getLatitude());
        response.setLongitude(ipLocation.getLongitude());
        response.setRegionName(ipLocation.getRegionName());
        return response;
    }
}
