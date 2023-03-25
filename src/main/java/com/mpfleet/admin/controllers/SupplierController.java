package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Supplier;
import com.mpfleet.admin.services.CountryService;
import com.mpfleet.admin.services.StateService;
import com.mpfleet.admin.services.SupplierService;
import com.mpfleet.interceptor.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/admin/suppliers")
    @PageTitle("Suppliers")
    public String getAllPages(Model model, String keyword){
        return getOnePage(model, 1, keyword);
    }

    @GetMapping("/admin/suppliers/page/{pageNumber}")
    @PageTitle("Suppliers")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
        Page<Supplier> page = supplierService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        page.getContent();
        List<Supplier> suppliers;
        suppliers = keyword == null? supplierService.findPage(currentPage).getContent():supplierService.findByKeyword(keyword);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("suppliers", suppliers);

        return "admin/supplier/allSuppliers";
    }

    @GetMapping("/admin/addsupplier")
    @PageTitle("Add Supplier")
    public String addSupplier(Model model){
        addModelAttributes(model);
        return "admin/supplier/addSupplier";
    }

    @PostMapping("/admin/suppliers")
    public String save(Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/admin/suppliers";
    }

    @GetMapping("/admin/supplier/{op}/{id}")
    @PageTitle("Edit Supplier")
    public String editSupplier(@PathVariable Long id, @PathVariable String op, Model model){
        Supplier supplier = supplierService.findById(id);
        model.addAttribute("supplier", supplier);
        addModelAttributes(model);
        return "/admin/supplier/"+ op + "Supplier";
    }

    @RequestMapping(value="/admin/suppliers/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@PathVariable Long id) {
        supplierService.deleteById(id);
        return "redirect:/admin/suppliers";
    }
}
