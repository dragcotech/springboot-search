package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Supplier;
import com.mpfleet.admin.services.CountryService;
import com.mpfleet.admin.services.StateService;
import com.mpfleet.admin.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SupplierController {

    private final SupplierService supplierService;
    private final CountryService countryService;
    private final StateService stateService;

    @Autowired
    public SupplierController(SupplierService supplierService, CountryService countryService, StateService stateService) {
        this.supplierService = supplierService;
        this.countryService = countryService;
        this.stateService = stateService;
    }

    public void addModelAttributes(Model model){
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("states", stateService.findAll());
    }

    @GetMapping("/suppliers")
    public String findAll(Model model){
        addModelAttributes(model);
        return "admin/supplier/allSuppliers";
    }

    @GetMapping("/addsupplier")
    public String addSupplier(Model model){
        addModelAttributes(model);
        return "admin/supplier/addSupplier";
    }

    @PostMapping("/suppliers")
    public String save(Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/supplier/{op}/{id}")
    public String editSupplier(@PathVariable Long id, @PathVariable String op, Model model){
        Supplier supplier = supplierService.findById(id);
        model.addAttribute("supplier", supplier);
        addModelAttributes(model);
        return "/admin/supplier/"+ op + "Supplier";
    }

    @RequestMapping(value="/suppliers/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@PathVariable Long id) {
        supplierService.deleteById(id);
        return "redirect:/suppliers";
    }
}
