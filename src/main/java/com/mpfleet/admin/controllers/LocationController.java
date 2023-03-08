package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Location;
import com.mpfleet.admin.services.CountryService;
import com.mpfleet.admin.services.LocationService;
import com.mpfleet.admin.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LocationController {
    private final LocationService locationService;
    private final CountryService countryService;
    private final StateService stateService;

    @Autowired
    public LocationController(LocationService locationService, CountryService countryService, StateService stateService) {
        this.locationService = locationService;
        this.countryService = countryService;
        this.stateService = stateService;
    }

    public void addModelAttributes(Model model){
        model.addAttribute("locations", locationService.getAll());
        model.addAttribute("countries", countryService.getAll());
        model.addAttribute("states", stateService.getAll());
    }

    @GetMapping("/locations")
    public String findAll(Model model){
        addModelAttributes(model);
        return "admin/allLocations";
    }

    @GetMapping("/addLocation")
    public String addLocation(Model model){
        model.addAttribute("countries", countryService.getAll());
        model.addAttribute("state", stateService.getAll());
        return "admin/addLocation";
    }

    @PostMapping("/locations")
    public String save(Location location) {
        locationService.save(location);
        return "redirect:/locations";
    }
}
