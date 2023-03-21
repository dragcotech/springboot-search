package com.mpfleet.fleet.repositories;

import com.mpfleet.fleet.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT v FROM Vehicle v WHERE v.name LIKE %?1% OR v.description LIKE %?1% " +
            "OR v.vehicleModel.description LIKE %?1% OR v.currentLocation.address LIKE %?1% OR v.netWeight LIKE %?1% OR v.power LIKE %?1%" +
            "OR v.inCharge.fullName LIKE %?1% OR v.vehicleMake.description LIKE %?1% OR v.vehicleType.description LIKE %?1%")
    List<Vehicle> findByKeyword(String keyword);
}
