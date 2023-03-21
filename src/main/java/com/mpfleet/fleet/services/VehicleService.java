package com.mpfleet.fleet.services;

import com.mpfleet.fleet.models.Vehicle;
import com.mpfleet.fleet.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

	private final VehicleRepository vehicleRepository;

	@Autowired
	public VehicleService(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	public List<Vehicle> findAll(){
		return vehicleRepository.findAll();
	}

	public Page<Vehicle> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber -1, 10);
		return vehicleRepository.findAll(pageable);
	}

	public Vehicle findById(Long id) {
		return vehicleRepository.findById(id).orElse(null);
	}	

	public void delete(Long id) {
		vehicleRepository.deleteById(id);
	}

	public void save(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}

	public List<Vehicle> findByKeyword(String keyword){
		return vehicleRepository.findByKeyword(keyword);
	}
}
