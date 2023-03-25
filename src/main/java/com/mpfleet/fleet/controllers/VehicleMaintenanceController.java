package com.mpfleet.fleet.controllers;

import com.mpfleet.admin.services.SupplierService;
import com.mpfleet.fleet.models.VehicleMaintenance;
import com.mpfleet.fleet.services.VehicleMaintenanceService;
import com.mpfleet.fleet.services.VehicleService;
import com.mpfleet.interceptor.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VehicleMaintenanceController {

	private final VehicleMaintenanceService vehicleMaintenanceService;
	private final VehicleService vehicleService;
	private final SupplierService supplierService;

	@Autowired
	public VehicleMaintenanceController(VehicleMaintenanceService vehicleMaintenanceService
			, VehicleService vehicleService, SupplierService supplierService) {
		this.vehicleMaintenanceService = vehicleMaintenanceService;
		this.vehicleService = vehicleService;
		this.supplierService = supplierService;
	}

	public void addModelAttributes(Model model){
		model.addAttribute("vehicles", vehicleService.findAll());
		model.addAttribute("suppliers", supplierService.findAll());
		model.addAttribute("maintenances", vehicleMaintenanceService.findAll());
	}

	@GetMapping("/fleet/vehiclemaintenances")
	@PageTitle("Vehicle Maintenance")
	public String getAllPages(Model model, String keyword){
		return getOnePage(model, 1, keyword);
	}

	@GetMapping("/fleet/vehiclemaintenances/page/{pageNumber}")
	@PageTitle("Vehicle Maintenance")
	public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
		Page<VehicleMaintenance> page = vehicleMaintenanceService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		page.getContent();
		List<VehicleMaintenance> maintenances;
		maintenances = keyword == null? vehicleMaintenanceService.findPage(currentPage).getContent():vehicleMaintenanceService.findByKeyword(keyword);

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("maintenances", maintenances);

		return "fleet/vehiclemaintenance/maintenances";
	}

	@GetMapping("/fleet/addmaintenances")
	@PageTitle("Add Vehicle Maintenance")
	public String addMaintenance(Model model){
		addModelAttributes(model);
		return "/fleet/vehiclemaintenance/addMaintenance";
	}

	@GetMapping("/fleet/maintenance/{op}/{id}")
	@PageTitle("Edit/Details Vehicle Maintenance")
	public String editMaintenance(Model model, @PathVariable Long id, @PathVariable String op){
		VehicleMaintenance maintenance = vehicleMaintenanceService.findById(id);
		model.addAttribute("maintenance", maintenance);
		addModelAttributes(model);
		return "/fleet/vehiclemaintenance/"+op+"Maintenance";
	}

	@PostMapping("/fleet/vehiclemaintenances")
	public String addNew(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceService.save(vehicleMaintenance);
		return "redirect:/fleet/vehiclemaintenances";
	}
	
	@RequestMapping(value="/fleet/maintenance/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		vehicleMaintenanceService.delete(id);
		return "redirect:/fleet/vehiclemaintenances";
	}
}