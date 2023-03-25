package com.mpfleet.fleet.controllers;

import com.mpfleet.admin.services.LocationService;
import com.mpfleet.fleet.models.Vehicle;
import com.mpfleet.fleet.services.*;
import com.mpfleet.hr.services.EmployeeService;
import com.mpfleet.interceptor.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@GetMapping("/fleet/vehicles")
	@PageTitle("Vehicles")
	public String getAllPages(Model model, String keyword){
		return getOnePage(model, 1, keyword);
	}

	@GetMapping("/fleet/vehicles/page/{pageNumber}")
	@PageTitle("Vehicles")
	public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
		Page<Vehicle> page = vehicleService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		page.getContent();
		List<Vehicle> vehicles;
		vehicles = keyword == null? vehicleService.findPage(currentPage).getContent():vehicleService.findByKeyword(keyword);

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("vehicles", vehicles);

		return "fleet/vehicle/vehicles";
	}

	@GetMapping("/fleet/addvehicle")
	@PageTitle("Add Vehicle")
	public String addVehicle(Model model){
		addModelAttributes(model);
		return "/fleet/vehicle/addVehicle";
	}

	@GetMapping("/fleet/vehicle/{op}/{id}")
	@PageTitle("Edit/Details Vehicle")
	public String editVehicle(@PathVariable Long id, @PathVariable String op, Model model){
		Vehicle vehicle = vehicleService.findById(id);
		model.addAttribute("vehicle", vehicle);
		addModelAttributes(model);
		return "/fleet/vehicle/"+ op + "Vehicle";
	}

	@PostMapping("/fleet/vehicles")
	public String addNew(Vehicle vehicle) {
		vehicleService.save(vehicle);
		return "redirect:/fleet/vehicles";
	}	

	@RequestMapping(value="/fleet/vehicle/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleService.delete(id);
		return "redirect:/fleet/vehicles";
	}
}
