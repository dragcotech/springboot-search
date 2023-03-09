package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Country;
import com.mpfleet.admin.models.State;
import com.mpfleet.admin.services.CountryService;
import com.mpfleet.admin.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StateController {

    private final StateService stateService;
    private final CountryService countryService;

    public void addModelAttribute(Model model){
        model.addAttribute("states", stateService.getAll());
        model.addAttribute("countries", countryService.getAll());
    }

    @Autowired
    public StateController(StateService stateService, CountryService countryService) {
        this.stateService = stateService;
        this.countryService = countryService;
    }

    @GetMapping("/states")
    public String getAll(Model model){ // ALL STATES
        addModelAttribute(model);
        return "admin/state/allStates";
    }

    @GetMapping("/addstate")
    public String addCountry(Model model){ // ADD STATE
        addModelAttribute(model);
        return "admin/state/addState";
    }

    @GetMapping("/editstate/{id}")
    public String editCountry(@PathVariable Long id, Model model){ // EDIT STATE
        State state = stateService.getById(id);
        Country country = countryService.getById(id);
        model.addAttribute("state", state);
        model.addAttribute("countries", country);
        return "admin/state/editState";
    }

    @GetMapping("/detailsstate/{id}")
    public String detailsCountry(@PathVariable Long id, Model model){ // DETAILS STATE
        State state = stateService.getById(id);
        Country country = countryService.getById(id);
        model.addAttribute("state", state);
        model.addAttribute("countries", country);
        return "admin/state/detailsState";
    }

    @PostMapping("/states")
    public String save(State state){ // SAVE STATE AND REDIRECT TO TABLE
        stateService.save(state);
        return "redirect:/states";
    }

    @RequestMapping(value = "/states/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Long id){ // DELETE STATE AND REDIRECT TO TABLE
        stateService.delete(id);
        return "redirect:/states";
    }

    @RequestMapping(value = "/states/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(State state){ // UPDATE THE STATE AND PUT IT BACK IN THE DATABASE
        stateService.save(state);
        return "redirect:/states";
    }
}
