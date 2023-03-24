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

	@GetMapping("/fleet/vehiclemakes")
	public String findAll(Model model){		
		model.addAttribute("vehicleMakes", vehicleMakeService.findAll());
		return "/fleet/vehiclemake/vehicleMakes";
	}	
	
	@RequestMapping("/fleet/vehiclemakes/{id}")
	@ResponseBody
	public Optional<VehicleMake> findById(@PathVariable Long id) {
		return vehicleMakeService.findById(id);
	}

	@PostMapping(value="/fleet/vehiclemakes")
	public String addNew(VehicleMake vehicleMake) {
		vehicleMakeService.save(vehicleMake);
		return "redirect:/fleet/vehiclemakes";
	}
	
	@RequestMapping(value="/fleet/vehiclemakes/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleMakeService.delete(id);
		return "redirect:/fleet/vehiclemakes";
	}
}
