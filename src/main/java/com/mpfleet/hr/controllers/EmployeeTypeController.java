package com.mpfleet.hr.controllers;

import com.mpfleet.hr.models.EmployeeType;
import com.mpfleet.hr.repositories.EmployeeTypeRepository;
import com.mpfleet.hr.services.EmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeTypeController {

	private final EmployeeTypeRepository employeeTypeRepository;
	private final EmployeeTypeService employeeTypeService;

	@Autowired
	public EmployeeTypeController(EmployeeTypeRepository employeeTypeRepository, EmployeeTypeService employeeTypeService) {
		this.employeeTypeRepository = employeeTypeRepository;
		this.employeeTypeService = employeeTypeService;
	}

	@GetMapping("/employeetypes")
	public String parameters(Model model){
		List<EmployeeType> employeeTypes = employeeTypeRepository.findAll();
		model.addAttribute("employeeTypes", employeeTypes);
		return "hr/employeetypes/employeeTypes";
	}

	@GetMapping("/employeetypes/{id}")
	@ResponseBody
	public EmployeeType getById(@PathVariable Long id){
		return employeeTypeService.findById(id).orElse(null);
	}

	@PostMapping("/employeetypes")
	public String save(EmployeeType employeeType){
		employeeTypeService.save(employeeType);
		return "redirect:/employeetypes";
	}

	@RequestMapping(value="/employeetypes/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		employeeTypeService.delete(id);
		return "redirect:/employeetypes";
	}
}
