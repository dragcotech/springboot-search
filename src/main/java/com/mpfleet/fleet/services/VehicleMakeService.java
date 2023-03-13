package com.mpfleet.fleet.services;

import com.mpfleet.fleet.models.VehicleMake;
import com.mpfleet.fleet.repositories.VehicleMakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleMakeService {

	private final VehicleMakeRepository vehicleMakeRepository;

	@Autowired
	public VehicleMakeService(VehicleMakeRepository vehicleMakeRepository) {
		this.vehicleMakeRepository = vehicleMakeRepository;
	}

	public List<VehicleMake> findAll(){
		return vehicleMakeRepository.findAll();
	}	

	public Optional<VehicleMake> findById(Long id) {
		return vehicleMakeRepository.findById(id);
	}	

	public void delete(Long id) {
		vehicleMakeRepository.deleteById(id);
	}

	public void save(VehicleMake vehicleMake) {
		vehicleMakeRepository.save(vehicleMake);
	}
}
