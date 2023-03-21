package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Country;
import com.mpfleet.admin.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public String getAll(Model model, String keyword){ // MAIN

        List<Country> countries;

        countries = keyword == null? countryService.findAll():countryService.findByKeyword(keyword);
        model.addAttribute("countries", countries);

        return "admin/country/allCountries";
    }

    @GetMapping("/addcountry")
    public String addCountry(){ // ADD COUNTRY
        return "admin/country/addCountry";
    }

    @GetMapping("/editcountry/{id}")
    public String editCountry(@PathVariable Long id, Model model){ // EDIT COUNTRY
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "admin/country/editCountry";
    }

    @GetMapping("/detailscountry/{id}")
    public String detailsCountry(@PathVariable Long id, Model model){ // DETAILS COUNTRY
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "admin/country/detailsCountry";
    }

    @PostMapping("/countries")
    public String save(Country country){ // SAVE COUNTRY AND REDIRECT TO TABLE
        countryService.save(country);
        return "redirect:/countries";
    }

    @GetMapping("/countries/{id}")
    @ResponseBody
    public Country getCountry(@PathVariable Long id){ // GET COUNTRY BY ID
        return countryService.getById(id);
    }

    @RequestMapping(value = "/countries/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Long id){ // DELETE COUNTRY AND REDIRECT TO TABLE
        countryService.delete(id);
        return "redirect:/countries";
    }

    @RequestMapping(value = "/countries/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Country country){ // UPDATE THE OBJECT AND PUT IT BACK IN THE DATABASE
        countryService.save(country);
        return "redirect:/countries";
    }
}
