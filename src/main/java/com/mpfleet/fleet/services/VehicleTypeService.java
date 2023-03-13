package com.mpfleet.fleet.services;

import com.mpfleet.fleet.models.VehicleType;
import com.mpfleet.fleet.repositories.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleTypeService {

	private final VehicleTypeRepository vehicleTypeRepository;

	@Autowired
	public VehicleTypeService(VehicleTypeRepository vehicleTypeRepository) {
		this.vehicleTypeRepository = vehicleTypeRepository;
	}

	public List<VehicleType> findAll(){
		return vehicleTypeRepository.findAll();
	}	

	public Optional<VehicleType> findById(Long id) {
		return vehicleTypeRepository.findById(id);
	}	

	public void delete(Long id) {
		vehicleTypeRepository.deleteById(id);
	}

	public void save(VehicleType vehicleType) {
		vehicleTypeRepository.save(vehicleType);
	}
}
