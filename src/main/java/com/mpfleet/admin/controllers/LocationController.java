package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Location;
import com.mpfleet.admin.services.CountryService;
import com.mpfleet.admin.services.LocationService;
import com.mpfleet.admin.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("states", stateService.findAll());
    }

    @GetMapping("/locations")
    public String findAll(Model model, String keyword){
        List<Location> locations;

        locations = keyword == null? locationService.findAll():locationService.findByKeyword(keyword);
        model.addAttribute("locations", locations);

        return "admin/location/allLocations";
    }

    @GetMapping("/addlocation")
    public String addLocation(Model model){
        addModelAttributes(model);
        return "admin/location/addLocation";
    }

    @PostMapping("/locations")
    public String save(Location location) {
        locationService.save(location);
        return "redirect:/locations";
    }

    @GetMapping("/detailslocation/{id}")
    public String detailsLocation(@PathVariable Long id, Model model){ // DETAILS COUNTRY
        Location location = locationService.findById(id);
        model.addAttribute("location", location);
        addModelAttributes(model);
        return "admin/location/detailsLocation";
    }

    @GetMapping("/editlocation/{id}")
    public String editLocation(@PathVariable Long id, Model model){ // EDIT COUNTRY
        Location location = locationService.findById(id);
        model.addAttribute("location", location);
        addModelAttributes(model);
        return "admin/location/editLocation";
    }

    @RequestMapping(value="/deletelocation/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@PathVariable Long id) {
        locationService.deleteById(id);
        return "redirect:/locations";
    }
}
