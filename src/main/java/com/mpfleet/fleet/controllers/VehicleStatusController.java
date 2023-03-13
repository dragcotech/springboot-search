package com.mpfleet.fleet.controllers;

import com.mpfleet.fleet.models.VehicleStatus;
import com.mpfleet.fleet.services.VehicleStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class VehicleStatusController {
	
	private final VehicleStatusService vehicleStatusService;

	@Autowired
	public VehicleStatusController(VehicleStatusService vehicleStatusService) {
		this.vehicleStatusService = vehicleStatusService;
	}


	@GetMapping("/vehiclestatus")
	public String findAll(Model model){		
		model.addAttribute("vehicleStatuses", vehicleStatusService.findAll());
		return "/fleet/vehiclestatus/vehicleStatus";
	}	
	
	@RequestMapping("/vehiclestatus/{id}")
	@ResponseBody
	public Optional<VehicleStatus> findById(@PathVariable Long id)
	{
		return vehicleStatusService.findById(id);
	}
	

	@PostMapping(value="/vehiclestatus")
	public String addNew(VehicleStatus vehicleStatus) {
		vehicleStatusService.save(vehicleStatus);
		return "redirect:/vehiclestatus";
	}	

	@RequestMapping(value="/vehiclestatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleStatusService.delete(id);
		return "redirect:/vehiclestatus";
	}
}
