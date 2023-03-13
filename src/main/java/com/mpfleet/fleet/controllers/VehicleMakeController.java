package com.mpfleet.fleet.controllers;

import com.mpfleet.fleet.models.VehicleMake;
import com.mpfleet.fleet.services.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class VehicleMakeController {
	
	private final VehicleMakeService vehicleMakeService;

	@Autowired
	public VehicleMakeController(VehicleMakeService vehicleMakeService) {
		this.vehicleMakeService = vehicleMakeService;
	}

	@GetMapping("/vehiclemakes")
	public String findAll(Model model){		
		model.addAttribute("vehicleMakes", vehicleMakeService.findAll());
		return "/fleet/vehiclemake/vehicleMakes";
	}	
	
	@RequestMapping("/vehiclemakes/{id}")
	@ResponseBody
	public Optional<VehicleMake> findById(@PathVariable Long id) {
		return vehicleMakeService.findById(id);
	}

	@PostMapping(value="/vehiclemakes")
	public String addNew(VehicleMake vehicleMake) {
		vehicleMakeService.save(vehicleMake);
		return "redirect:/vehiclemakes";
	}
	
	@RequestMapping(value="vehiclemakes/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleMakeService.delete(id);
		return "redirect:/vehiclemakes";
	}
}
