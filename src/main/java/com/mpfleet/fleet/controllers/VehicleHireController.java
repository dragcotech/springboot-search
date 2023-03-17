package com.mpfleet.fleet.controllers;

import com.mpfleet.admin.services.ClientService;
import com.mpfleet.admin.services.LocationService;
import com.mpfleet.fleet.models.VehicleHire;
import com.mpfleet.fleet.services.VehicleHireService;
import com.mpfleet.fleet.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VehicleHireController {
	
	private final VehicleHireService vehicleHireService;
	private final ClientService clientService;
	private final LocationService locationService;
	private final VehicleService vehicleService;

	@Autowired
	public VehicleHireController(VehicleHireService vehicleHireService, ClientService clientService, LocationService locationService, VehicleService vehicleService) {
		this.vehicleHireService = vehicleHireService;
		this.clientService = clientService;
		this.locationService = locationService;
		this.vehicleService = vehicleService;
	}

	public void addModelAttributes(Model model){
		model.addAttribute("clients", clientService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("vehicles", vehicleService.findAll());
	}

	@GetMapping("/vehiclehires")
	public String findAll(Model model){		
		model.addAttribute("hires", vehicleHireService.findAll());
		return "/fleet/vehiclehire/hires";
	}

	@GetMapping("/addhire")
	public String addHire(Model model){
		addModelAttributes(model);
		return "/fleet/vehiclehire/addHire";
	}

	@GetMapping("/hire/{op}/{id}")
	public String editHire(Model model, @PathVariable Long id, @PathVariable String op){
		VehicleHire hire = vehicleHireService.findById(id);
		model.addAttribute("hire", hire);
		addModelAttributes(model);
		return "/fleet/vehiclehire/"+op+"Hire";
	}


	@PostMapping("/vehiclehires")
	public String addNew(VehicleHire vehicleHire) {
		vehicleHireService.save(vehicleHire);
		return "redirect:/vehiclehires";
	}
	
	@RequestMapping(value="/hire/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleHireService.delete(id);
		return "redirect:/vehiclehires";
	}

}