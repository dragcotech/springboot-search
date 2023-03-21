package com.mpfleet.fleet.repositories;

import com.mpfleet.fleet.models.VehicleHire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleHireRepository extends JpaRepository<VehicleHire, Long> {

    @Query(value = "SELECT vh FROM VehicleHire vh WHERE vh.client.name LIKE %?1% OR vh.client.website LIKE %?1% " +
            "OR vh.location.address LIKE %?1% OR vh.vehicle.name LIKE %?1% OR vh.price LIKE %?1% OR vh.vehicle.vehicleModel.description LIKE %?1%" +
            "OR vh.remarks LIKE %?1% OR vh.timeIn LIKE %?1% OR vh.timeOut LIKE %?1%")
    List<VehicleHire> findByKeyword(String keyword);
}
