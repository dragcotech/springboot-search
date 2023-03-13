package com.mpfleet.fleet.services;

import com.mpfleet.fleet.models.VehicleStatus;
import com.mpfleet.fleet.repositories.VehicleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleStatusService {

	private final VehicleStatusRepository vehicleStatusRepository;

	@Autowired
	public VehicleStatusService(VehicleStatusRepository vehicleStatusRepository) {
		this.vehicleStatusRepository = vehicleStatusRepository;
	}

	public List<VehicleStatus> findAll(){
		return vehicleStatusRepository.findAll();
	}	

	public Optional<VehicleStatus> findById(Long id) {
		return vehicleStatusRepository.findById(id);
	}	

	public void delete(Long id) {
		vehicleStatusRepository.deleteById(id);
	}

	public void save(VehicleStatus vehicleStatus) {
		vehicleStatusRepository.save(vehicleStatus);
	}
}
