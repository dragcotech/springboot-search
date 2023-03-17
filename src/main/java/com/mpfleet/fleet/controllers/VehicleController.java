package com.mpfleet.fleet.controllers;

import com.mpfleet.admin.services.LocationService;
import com.mpfleet.fleet.models.Vehicle;
import com.mpfleet.fleet.services.*;
import com.mpfleet.hr.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleController {
	
	private final VehicleService vehicleService;
	private final VehicleTypeService vehicleTypeService;
	private final VehicleMakeService vehicleMakeService;
	private final VehicleModelService vehicleModelService;
	private final LocationService locationService;
	private final EmployeeService employeeService ;
	private final VehicleStatusService vehicleStatusService;

	@Autowired
	public VehicleController(VehicleService vehicleService, VehicleTypeService vehicleTypeService,
							 VehicleMakeService vehicleMakeService, VehicleModelService vehicleModelService,
							 LocationService locationService, EmployeeService employeeService,
							 VehicleStatusService vehicleStatusService) {
		this.vehicleService = vehicleService;
		this.vehicleTypeService = vehicleTypeService;
		this.vehicleMakeService = vehicleMakeService;
		this.vehicleModelService = vehicleModelService;
		this.locationService = locationService;
		this.employeeService = employeeService;
		this.vehicleStatusService = vehicleStatusService;
	}

	public void addModelAttributes(Model model){
		model.addAttribute("vehicles", vehicleService.findAll());
		model.addAttribute("vehicleTypes", vehicleTypeService.findAll());
		model.addAttribute("vehicleModels", vehicleModelService.findAll());
		model.addAttribute("vehicleMakes", vehicleMakeService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("vehicleStatuses", vehicleStatusService.findAll());
	}

	@GetMapping("/vehicles")
	public String findAll(Model model){		
		addModelAttributes(model);
		return "/fleet/vehicle/vehicles";
	}

	@GetMapping("/addvehicle")
	public String addVehicle(Model model){
		addModelAttributes(model);
		return "/fleet/vehicle/addVehicle";
	}

	@GetMapping("/vehicle/{op}/{id}")
	public String editVehicle(@PathVariable Long id, @PathVariable String op, Model model){
		Vehicle vehicle = vehicleService.findById(id);
		model.addAttribute("vehicle", vehicle);
		addModelAttributes(model);
		return "/fleet/vehicle/"+ op + "Vehicle";
	}

	@PostMapping("/vehicles")
	public String addNew(Vehicle vehicle) {
		vehicleService.save(vehicle);
		return "redirect:/vehicles";
	}	

	@RequestMapping(value="/vehicle/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleService.delete(id);
		return "redirect:/vehicles";
	}
}
