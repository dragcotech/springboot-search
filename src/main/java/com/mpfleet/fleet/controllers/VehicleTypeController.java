package com.mpfleet.fleet.controllers;

import com.mpfleet.fleet.models.VehicleType;
import com.mpfleet.fleet.services.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class VehicleTypeController {

	private final VehicleTypeService vehicleTypeService;

	@Autowired
	public VehicleTypeController(VehicleTypeService vehicleTypeService) {
		this.vehicleTypeService = vehicleTypeService;
	}

	@GetMapping("/vehicletypes")
	public String findAll(Model model){
		model.addAttribute("vehicleTypes", vehicleTypeService.findAll());
		return "/fleet/vehicletype/vehicleTypes";
	}

	@RequestMapping("/vehicletype/{id}")
	@ResponseBody
	public Optional<VehicleType> findById(@PathVariable Long id) {
		return vehicleTypeService.findById(id);
	}

	@PostMapping(value="/vehicletypes")
	public String addNew(VehicleType vehicleType) {
		vehicleTypeService.save(vehicleType);
		return "redirect:/vehicletypes";
	}

	@RequestMapping(value="/vehicletype/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleTypeService.delete(id);
		return "redirect:/vehicletypes";
	}
}
