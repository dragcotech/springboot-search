package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Country;
import com.mpfleet.admin.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public String getAll(Model model){

        List<Country> countries = countryService.getAll();
        model.addAttribute("countries", countries);

        return "admin/allCountries";
    }
}
