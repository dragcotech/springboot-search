package com.mpfleet.fleet.repositories;

import com.mpfleet.fleet.models.VehicleMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleMakeRepository extends JpaRepository<VehicleMake, Long> {

}
