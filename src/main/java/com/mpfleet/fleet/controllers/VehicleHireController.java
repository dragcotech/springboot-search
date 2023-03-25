package com.mpfleet.fleet.controllers;

import com.mpfleet.admin.services.ClientService;
import com.mpfleet.admin.services.LocationService;
import com.mpfleet.fleet.models.VehicleHire;
import com.mpfleet.fleet.services.VehicleHireService;
import com.mpfleet.fleet.services.VehicleService;
import com.mpfleet.interceptor.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@GetMapping("/fleet/vehiclehires")
	@PageTitle("Vehicle Hires")
	public String getAllPages(Model model, String keyword){
		return getOnePage(model, 1, keyword);
	}

	@GetMapping("/fleet/vehiclehires/page/{pageNumber}")
	@PageTitle("Vehicle Hires")
	public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
		Page<VehicleHire> page = vehicleHireService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		page.getContent();
		List<VehicleHire> hires;
		hires = keyword == null? vehicleHireService.findPage(currentPage).getContent():vehicleHireService.findByKeyword(keyword);

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("hires", hires);

		return "fleet/vehiclehire/hires";
	}

	@GetMapping("/fleet/addhire")
	@PageTitle("Add Vehicle Hire")
	public String addHire(Model model){
		addModelAttributes(model);
		return "/fleet/vehiclehire/addHire";
	}

	@GetMapping("/fleet/hire/{op}/{id}")
	@PageTitle("Edit/Details Vehicle Hire")
	public String editHire(Model model, @PathVariable Long id, @PathVariable String op){
		VehicleHire hire = vehicleHireService.findById(id);
		model.addAttribute("hire", hire);
		addModelAttributes(model);
		return "/fleet/vehiclehire/"+op+"Hire";
	}


	@PostMapping("/fleet/vehiclehires")
	public String addNew(VehicleHire vehicleHire) {
		vehicleHireService.save(vehicleHire);
		return "redirect:/fleet/vehiclehires";
	}
	
	@RequestMapping(value="/fleet/hire/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleHireService.delete(id);
		return "redirect:/fleet/vehiclehires";
	}

}
