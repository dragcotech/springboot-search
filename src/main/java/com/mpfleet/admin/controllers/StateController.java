package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Country;
import com.mpfleet.admin.models.State;
import com.mpfleet.admin.services.CountryService;
import com.mpfleet.admin.services.StateService;
import com.mpfleet.interceptor.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StateController {

    private final StateService stateService;
    private final CountryService countryService;

    public void addModelAttribute(Model model){
        model.addAttribute("states", stateService.findAll());
        model.addAttribute("countries", countryService.findAll());
    }

    @Autowired
    public StateController(StateService stateService, CountryService countryService) {
        this.stateService = stateService;
        this.countryService = countryService;
    }

    @GetMapping("/admin/states")
    @PageTitle("States")
    public String getAllPages(Model model, String keyword){
        return getOnePage(model, 1, keyword);
    }

    @GetMapping("/admin/states/page/{pageNumber}")
    @PageTitle("States")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
        Page<State> page = stateService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        page.getContent();
        List<State> states;
        states = keyword == null? stateService.findPage(currentPage).getContent():stateService.findByKeyword(keyword);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("states", states);

        return "admin/state/allStates";
    }

    @GetMapping("/admin/addstate")
    @PageTitle("Add State")
    public String addCountry(Model model){ // ADD STATE
        addModelAttribute(model);
        return "admin/state/addState";
    }

    @GetMapping("/admin/editstate/{id}")
    @PageTitle("Edit State")
    public String editCountry(@PathVariable Long id, Model model){ // EDIT STATE
        State state = stateService.getById(id);
        Country country = countryService.getById(id);
        model.addAttribute("state", state);
        model.addAttribute("countries", country);
        return "admin/state/editState";
    }

    @GetMapping("/admin/detailsstate/{id}")
    @PageTitle("Details State")
    public String detailsCountry(@PathVariable Long id, Model model){ // DETAILS STATE
        State state = stateService.getById(id);
        Country country = countryService.getById(id);
        model.addAttribute("state", state);
        model.addAttribute("countries", country);
        return "admin/state/detailsState";
    }

    @PostMapping("/admin/states")
    public String save(State state){ // SAVE STATE AND REDIRECT TO TABLE
        stateService.save(state);
        return "redirect:/admin/states";
    }

    @RequestMapping(value = "/admin/states/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Long id){ // DELETE STATE AND REDIRECT TO TABLE
        stateService.delete(id);
        return "redirect:/admin/states";
    }

    @RequestMapping(value = "/admin/states/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(State state){ // UPDATE THE STATE AND PUT IT BACK IN THE DATABASE
        stateService.save(state);
        return "redirect:/admin/states";
    }
}
