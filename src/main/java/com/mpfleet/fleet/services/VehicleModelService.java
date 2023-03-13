package com.mpfleet.fleet.services;

import com.mpfleet.fleet.models.VehicleModel;
import com.mpfleet.fleet.repositories.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleModelService {

	private final VehicleModelRepository vehicleModelRepository;

	@Autowired
	public VehicleModelService(VehicleModelRepository vehicleModelRepository) {
		this.vehicleModelRepository = vehicleModelRepository;
	}

	public List<VehicleModel> findAll(){
		return vehicleModelRepository.findAll();
	}	

	public Optional<VehicleModel> findById(Long id) {
		return vehicleModelRepository.findById(id);
	}	

	public void delete(Long id) {
		vehicleModelRepository.deleteById(id);
	}

	public void save(VehicleModel vehicleModel) {
		vehicleModelRepository.save(vehicleModel);
	}
}
