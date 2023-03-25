package com.mpfleet.fleet.controllers;

import com.mpfleet.admin.services.LocationService;
import com.mpfleet.fleet.models.VehicleMovement;
import com.mpfleet.fleet.services.VehicleMovementService;
import com.mpfleet.fleet.services.VehicleService;
import com.mpfleet.interceptor.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


	@GetMapping("/fleet/vehiclemovements")
	@PageTitle("Vehicle Movements")
	public String getAllPages(Model model, String keyword){
		return getOnePage(model, 1, keyword);
	}

	@GetMapping("/fleet/vehiclemovements/page/{pageNumber}")
	@PageTitle("Vehicle Movements")
	public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
		Page<VehicleMovement> page = vehicleMovementService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		page.getContent();
		List<VehicleMovement> movements;
		movements = keyword == null? vehicleMovementService.findPage(currentPage).getContent():vehicleMovementService.findByKeyword(keyword);

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("movements", movements);

		return "fleet/vehiclemovement/movements";
	}

	@GetMapping("/fleet/addmovement")
	@PageTitle("Add Vehicle Movement")
	public String addMovement(Model model){
		addModelAttributes(model);
		return "/fleet/vehiclemovement/addMovement";
	}

	@GetMapping("/fleet/movement/{op}/{id}")
	@PageTitle("Edit/Details VehicleMovement")
	public String editMovement(Model model, @PathVariable Long id, @PathVariable String op){
		VehicleMovement movement = vehicleMovementService.findById(id);
		model.addAttribute("movement", movement);
		addModelAttributes(model);
		return "/fleet/vehiclemovement/"+op+"Movement";
	}


	@PostMapping("/fleet/vehiclemovements")
	public String addNew(VehicleMovement vehicleMovement) {
		vehicleMovementService.save(vehicleMovement);
		return "redirect:/fleet/vehiclemovements";
	}
	
	@RequestMapping(value="/fleet/movements/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleMovementService.delete(id);
		return "redirect:/fleet/vehiclemovements";
	}
}
