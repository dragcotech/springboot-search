package com.mpfleet.hr.controllers;

import com.mpfleet.hr.models.EmployeeStatus;
import com.mpfleet.hr.services.EmployeeStatusService;
import com.mpfleet.interceptor.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EmployeeStatusController {

    private final EmployeeStatusService employeeStatusService;

    @Autowired
    public EmployeeStatusController(EmployeeStatusService employeeStatusService) {
        this.employeeStatusService = employeeStatusService;
    }

    @GetMapping("/hr/employeestatuses")
    @PageTitle("Employee Statuses")
    public String parameters(Model model){
        List<EmployeeStatus> employeeStatuses = employeeStatusService.findAll();
        model.addAttribute("employeeStatuses", employeeStatuses);
        return "hr/employeestatus/employeeStatuses";
    }

    @GetMapping("/hr/employeestatuses/{id}")
    @ResponseBody
    public EmployeeStatus getById(@PathVariable Long id){
        return employeeStatusService.findById(id).orElse(null);
    }

    @PostMapping("/hr/employeestatuses")
    public String save(EmployeeStatus employeeStatus){
        employeeStatusService.save(employeeStatus);
        return "redirect:/hr/employeestatuses";
    }

    @RequestMapping(value="/hr/employeestatuses/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Long id) {
        employeeStatusService.delete(id);
        return "redirect:/hr/employeestatuses";
    }
    
}
