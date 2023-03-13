package com.mpfleet.fleet.repositories;

import com.mpfleet.fleet.models.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleStatusRepository extends JpaRepository<VehicleStatus, Long> {

}
