package geoip.repository;

import geoip.model.IpLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IpLocationRepository extends JpaRepository<IpLocation, Long> {
    @Query(value = "SELECT * FROM iplocation i WHERE :ipNumber >= i.ip_address_from "
            + "ORDER BY i.ip_address_from DESC LIMIT 1", nativeQuery = true)
    IpLocation findIPLocationInRange(@Param("ipNumber") long ipNumber);
}
