package com.mpfleet.fleet.repositories;

import com.mpfleet.fleet.models.VehicleMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMovementRepository extends JpaRepository<VehicleMovement, Long> {

    @Query(value = "SELECT vm FROM VehicleMovement vm WHERE vm.vehicle.name LIKE %?1% OR vm.vehicle.vehicleModel.description LIKE %?1% " +
            "OR vm.firstLocation.address LIKE %?1% OR vm.firstLocation.state.capital LIKE %?1% OR vm.firstLocation.country.name LIKE %?1% " +
            "OR vm.remarks LIKE %?1% OR vm.secondLocation.address LIKE %?1% OR vm.secondLocation.state.capital LIKE %?1% OR vm.firstLocation.country.name LIKE %?1%")
    List<VehicleMovement> findByKeyword(String keyword);
}
