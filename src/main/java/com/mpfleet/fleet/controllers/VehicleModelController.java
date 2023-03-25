package com.mpfleet.fleet.controllers;

import com.mpfleet.fleet.models.VehicleModel;
import com.mpfleet.fleet.services.VehicleModelService;
import com.mpfleet.interceptor.annotations.PageTitle;
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

	@GetMapping("/fleet/vehiclemodels")
	@PageTitle("Vehicle Models")
	public String findAll(Model model){		
		model.addAttribute("vehicleModels", vehicleModelService.findAll());
		return "/fleet/vehiclemodels/vehicleModels";
	}	
	
	@RequestMapping("/fleet/vehicleModel/{id}")
	@ResponseBody
	public Optional<VehicleModel> findById(@PathVariable Long id) {
		return vehicleModelService.findById(id);
	}

	@PostMapping(value="/fleet/vehiclemodels")
	public String addNew(VehicleModel vehicleModel) {
		vehicleModelService.save(vehicleModel);
		return "redirect:/fleet/vehiclemodels";
	}
	
	@RequestMapping(value="/fleet/vehiclemodel/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleModelService.delete(id);
		return "redirect:/fleet/vehiclemodels";
	}
}
