package com.mpfleet.fleet.controllers;

import com.mpfleet.fleet.models.VehicleModel;
import com.mpfleet.fleet.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class VehicleModelController {

	private final VehicleModelService vehicleModelService;

	@Autowired
	public VehicleModelController(VehicleModelService vehicleModelService) {
		this.vehicleModelService = vehicleModelService;
	}

	@GetMapping("/vehiclemodels")
	public String findAll(Model model){		
		model.addAttribute("vehicleModels", vehicleModelService.findAll());
		return "/fleet/vehiclemodels/vehicleModels";
	}	
	
	@RequestMapping("/vehicleModel/{id}")
	@ResponseBody
	public Optional<VehicleModel> findById(@PathVariable Long id) {
		return vehicleModelService.findById(id);
	}

	@PostMapping(value="/vehiclemodels")
	public String addNew(VehicleModel vehicleModel) {
		vehicleModelService.save(vehicleModel);
		return "redirect:/vehiclemodels";
	}
	
	@RequestMapping(value="/vehiclemodel/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleModelService.delete(id);
		return "redirect:/vehiclemodels";
	}
}
