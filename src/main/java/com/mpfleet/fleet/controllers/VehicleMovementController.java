package com.mpfleet.fleet.controllers;

import com.mpfleet.admin.services.LocationService;
import com.mpfleet.fleet.models.VehicleMovement;
import com.mpfleet.fleet.services.VehicleMovementService;
import com.mpfleet.fleet.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleMovementController {
    private final VehicleMovementService vehicleMovementService;
	private final LocationService locationService;
	private final VehicleService vehicleService;

	@Autowired
	public VehicleMovementController(VehicleMovementService vehicleMovementService, LocationService locationService, VehicleService vehicleService) {
		this.vehicleMovementService = vehicleMovementService;
		this.locationService = locationService;
		this.vehicleService = vehicleService;
	}

	public void addModelAttributes(Model model){
		model.addAttribute("firstLocation", locationService.findAll());
		model.addAttribute("secondLocation", locationService.findAll());
		model.addAttribute("vehicles", vehicleService.findAll());
	}


	@GetMapping("/vehiclemovements")
	public String findAll(Model model, String keyword){
		List<VehicleMovement> movements;

		movements = keyword == null? vehicleMovementService.findAll():vehicleMovementService.findByKeyword(keyword);

		model.addAttribute("movements", movements);
		return "/fleet/vehiclemovement/movements";
	}

	@GetMapping("/addmovement")
	public String addMovement(Model model){
		addModelAttributes(model);
		return "/fleet/vehiclemovement/addMovement";
	}

	@GetMapping("/movement/{op}/{id}")
	public String editMovement(Model model, @PathVariable Long id, @PathVariable String op){
		VehicleMovement movement = vehicleMovementService.findById(id);
		model.addAttribute("movement", movement);
		addModelAttributes(model);
		return "/fleet/vehiclemovement/"+op+"Movement";
	}


	@PostMapping("/vehiclemovements")
	public String addNew(VehicleMovement vehicleMovement) {
		vehicleMovementService.save(vehicleMovement);
		return "redirect:/vehiclemovements";
	}
	
	@RequestMapping(value="/movements/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleMovementService.delete(id);
		return "redirect:/vehiclemovements";
	}
}
