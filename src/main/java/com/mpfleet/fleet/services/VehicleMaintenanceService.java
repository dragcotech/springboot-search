package com.mpfleet.fleet.services;

import com.mpfleet.fleet.models.VehicleMaintenance;
import com.mpfleet.fleet.repositories.VehicleMaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleMaintenanceService {

	private final VehicleMaintenanceRepository vehicleMaintenanceRepository;

	@Autowired
	public VehicleMaintenanceService(VehicleMaintenanceRepository vehicleMaintenanceRepository) {
		this.vehicleMaintenanceRepository = vehicleMaintenanceRepository;
	}

	public List<VehicleMaintenance> findAll(){
		return vehicleMaintenanceRepository.findAll();
	}

	public Page<VehicleMaintenance> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber -1, 10);
		return vehicleMaintenanceRepository.findAll(pageable);
	}

	public VehicleMaintenance findById(Long id) {
		return vehicleMaintenanceRepository.findById(id).orElse(null);
	}	

	public void delete(Long id) {
		vehicleMaintenanceRepository.deleteById(id);
	}

	public void save(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceRepository.save(vehicleMaintenance);
	}

	public List<VehicleMaintenance> findByKeyword(String keyword){
		return vehicleMaintenanceRepository.findByKeyword(keyword);
	}
}
