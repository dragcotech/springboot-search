package com.mpfleet.fleet.controllers;

import com.mpfleet.admin.services.SupplierService;
import com.mpfleet.fleet.models.VehicleMaintenance;
import com.mpfleet.fleet.services.VehicleMaintenanceService;
import com.mpfleet.fleet.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleMaintenanceController {

	private final VehicleMaintenanceService vehicleMaintenanceService;
	private final VehicleService vehicleService;
	private final SupplierService supplierService;

	@Autowired
	public VehicleMaintenanceController(VehicleMaintenanceService vehicleMaintenanceService
			, VehicleService vehicleService, SupplierService supplierService) {
		this.vehicleMaintenanceService = vehicleMaintenanceService;
		this.vehicleService = vehicleService;
		this.supplierService = supplierService;
	}

	public void addModelAttributes(Model model){
		model.addAttribute("vehicles", vehicleService.findAll());
		model.addAttribute("suppliers", supplierService.findAll());
		model.addAttribute("maintenances", vehicleMaintenanceService.findAll());
	}

	@GetMapping("/vehiclemaintenances")
	public String findAll(Model model){		
		addModelAttributes(model);
		return "/fleet/vehiclemaintenance/maintenances";
	}

	@GetMapping("/addmaintenances")
	public String addMaintenance(Model model){
		addModelAttributes(model);
		return "/fleet/vehiclemaintenance/addMaintenance";
	}

	@GetMapping("/maintenance/{op}/{id}")
	public String editMaintenance(Model model, @PathVariable Long id, @PathVariable String op){
		VehicleMaintenance maintenance = vehicleMaintenanceService.findById(id);
		model.addAttribute("maintenance", maintenance);
		addModelAttributes(model);
		return "/fleet/vehiclemaintenance/"+op+"Maintenance";
	}

	@PostMapping("/vehiclemaintenances")
	public String addNew(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceService.save(vehicleMaintenance);
		return "redirect:/vehiclemaintenances";
	}
	
	@RequestMapping(value="/maintenance/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleMaintenanceService.delete(id);
		return "redirect:/vehiclemaintenances";
	}
}