package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Location;
import com.mpfleet.admin.services.CountryService;
import com.mpfleet.admin.services.LocationService;
import com.mpfleet.admin.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/admin/locations")
    public String getAllPages(Model model, String keyword){
        return getOnePage(model, 1, keyword);
    }

    @GetMapping("/admin/locations/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
        Page<Location> page = locationService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        page.getContent();
        List<Location> locations;
        locations = keyword == null? locationService.findPage(currentPage).getContent():locationService.findByKeyword(keyword);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("locations", locations);

        return "admin/location/allLocations";
    }

    @GetMapping("/admin/addlocation")
    public String addLocation(Model model){
        addModelAttributes(model);
        return "admin/location/addLocation";
    }

    @PostMapping("/admin/locations")
    public String save(Location location) {
        locationService.save(location);
        return "redirect:/admin/locations";
    }

    @GetMapping("/admin/detailslocation/{id}")
    public String detailsLocation(@PathVariable Long id, Model model){
        Location location = locationService.findById(id);
        model.addAttribute("location", location);
        addModelAttributes(model);
        return "admin/location/detailsLocation";
    }

    @GetMapping("/admin/editlocation/{id}")
    public String editLocation(@PathVariable Long id, Model model){
        Location location = locationService.findById(id);
        model.addAttribute("location", location);
        addModelAttributes(model);
        return "admin/location/editLocation";
    }

    @RequestMapping(value="/admin/deletelocation/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@PathVariable Long id) {
        locationService.deleteById(id);
        return "redirect:/admin/locations";
    }
}
