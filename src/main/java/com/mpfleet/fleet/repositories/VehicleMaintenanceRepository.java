package com.mpfleet.fleet.repositories;

import com.mpfleet.fleet.models.VehicleMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMaintenanceRepository extends JpaRepository<VehicleMaintenance, Long> {

    @Query(value = "SELECT vm FROM VehicleMaintenance vm WHERE vm.vehicle.name LIKE %?1% OR vm.vehicle.vehicleModel.description LIKE %?1% " +
            "OR vm.supplier.name LIKE %?1% OR vm.supplier.email LIKE %?1% OR vm.supplier.email LIKE %?1% OR vm.remarks LIKE %?1%" +
            "OR vm.price LIKE %?1%")
    List<VehicleMaintenance> findByKeyword(String keyword);
}
