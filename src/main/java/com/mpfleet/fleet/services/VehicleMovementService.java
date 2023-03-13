package com.mpfleet.fleet.services;

import com.mpfleet.fleet.models.VehicleMovement;
import com.mpfleet.fleet.repositories.VehicleMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleMovementService {

	private final VehicleMovementRepository vehicleMovementRepository;

	@Autowired
	public VehicleMovementService(VehicleMovementRepository vehicleMovementRepository) {
		this.vehicleMovementRepository = vehicleMovementRepository;
	}

	public List<VehicleMovement> findAll(){
		return vehicleMovementRepository.findAll();
	}	

	public VehicleMovement findById(Long id) {
		return vehicleMovementRepository.findById(id).orElse(null);
	}	

	public void delete(Long id) {
		vehicleMovementRepository.deleteById(id);
	}

	public void save(VehicleMovement vehicleMovement) {
		vehicleMovementRepository.save(vehicleMovement);
	}
}
