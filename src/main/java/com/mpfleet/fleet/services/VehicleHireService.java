package com.mpfleet.fleet.services;

import com.mpfleet.fleet.models.VehicleHire;
import com.mpfleet.fleet.repositories.VehicleHireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleHireService {

	private final VehicleHireRepository vehicleHireRepository;

	@Autowired
	public VehicleHireService(VehicleHireRepository vehicleHireRepository) {
		this.vehicleHireRepository = vehicleHireRepository;
	}

	public List<VehicleHire> findAll(){
		return vehicleHireRepository.findAll();
	}	

	public VehicleHire findById(Long id) {
		return vehicleHireRepository.findById(id).orElse(null);
	}	

	public void delete(Long id) {
		vehicleHireRepository.deleteById(id);
	}

	public void save(VehicleHire vehicleHire) {
		vehicleHireRepository.save(vehicleHire);
	}
}
