package com.mpfleet.hr.controllers;

import com.mpfleet.admin.services.CountryService;
import com.mpfleet.admin.services.StateService;
import com.mpfleet.hr.models.Employee;
import com.mpfleet.hr.services.EmployeeService;
import com.mpfleet.hr.services.EmployeeTypeService;
import com.mpfleet.hr.services.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class EmployeeController {
	
	private final EmployeeService employeeService;
	private final StateService stateService;
	private final JobTitleService jobTitleService;
	private final EmployeeTypeService employeeTypeService;
	private final CountryService countryService;

	@Autowired
	public EmployeeController(EmployeeService employeeService, StateService stateService
			, JobTitleService jobTitleService, EmployeeTypeService employeeTypeService, CountryService countryService) {
		this.employeeService = employeeService;
		this.stateService = stateService;
		this.jobTitleService = jobTitleService;
		this.employeeTypeService = employeeTypeService;
		this.countryService = countryService;
	}

	public void addModelAttributes(Model model){
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("states", stateService.findAll());
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("jobTitles", jobTitleService.findAll());
		model.addAttribute("employeeTypes", employeeTypeService.findAll());
	}

	@GetMapping("/employees")
	public String getAllPages(Model model, String keyword){
		return getOnePage(model, 1, keyword);
	}

	@GetMapping("/employees/page/{pageNumber}")
	public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
		Page<Employee> page = employeeService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		page.getContent();
		List<Employee> employees;
		employees = keyword == null? employeeService.findPage(currentPage).getContent():employeeService.findByKeyword(keyword);

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("employees", employees);

		return "hr/employee/employees";
	}

	@GetMapping("/addemployee")
	public String addEmployee(Model model){
		addModelAttributes(model);
		return "hr/employee/addEmployee";
	}


	@GetMapping("/employee/{op}/{id}")
	public String editEmployee(@PathVariable Long id, @PathVariable String op, Model model){
		Employee employee = employeeService.findById(id);
		model.addAttribute("employee", employee);
		addModelAttributes(model);
		return "/hr/employee/"+ op + "Employee";
	}

	@PostMapping("/employees")
	public String addNew(Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees";
	}	

	@RequestMapping(value="/employee/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		employeeService.delete(id);
		return "redirect:/employees";
	}

	@RequestMapping(value="/employee/profile")
	public String profile(Model model, Principal principal) {
		String un = principal.getName();
		addModelAttributes(model);
		model.addAttribute("employee", employeeService.findByUsername(un));
		return "assets/profile";
	}
}
