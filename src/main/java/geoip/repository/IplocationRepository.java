package geoip.repository;

import geoip.model.IPlocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IplocationRepository extends JpaRepository<IPlocation, Long> {

    @Query("SELECT i FROM IPlocation i "
            + "WHERE i.ipAddressFrom <= :ipNumber AND i.ipAddressTo >= :ipNumber")
    IPlocation findipLocationInrange(@Param("ipNumber") long ipNumber);
}
