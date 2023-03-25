package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Client;
import com.mpfleet.admin.services.ClientService;
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
public class ClientController {

    private final ClientService clientService;
    private final CountryService countryService;
    private final StateService stateService;

    @Autowired
    public ClientController(ClientService clientService, CountryService countryService, StateService stateService) {
        this.clientService = clientService;
        this.countryService = countryService;
        this.stateService = stateService;
    }

    public void addModelAttributes(Model model){
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("states", stateService.findAll());
    }

    @GetMapping("/admin/clients")
    @PageTitle("Clients")
    public String getAllPages(Model model, String keyword){
        return getOnePage(model, 1, keyword);
    }

    @GetMapping("/admin/clients/page/{pageNumber}")
    @PageTitle("Clients")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
        Page<Client> page = clientService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        page.getContent();
        List<Client> clients;
        clients = keyword == null? clientService.findPage(currentPage).getContent():clientService.findByKeyword(keyword);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("clients", clients);

        return "admin/client/allClients";
    }

    @GetMapping("/admin/addclient")
    @PageTitle("Add Client")
    public String addClient(Model model){
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("states", stateService.findAll());
        return "/admin/client/addClient";
    }

    @GetMapping("/admin/client/{op}/{id}")
    @PageTitle("Edit/Details Client")
    public String editClient(@PathVariable Long id, @PathVariable String op, Model model){
        Client client = clientService.findById(id);
        model.addAttribute("client", client);
        addModelAttributes(model);
        return "/admin/client/"+ op + "Client";
    }

    @PostMapping("/admin/clients")
    public String save(Client client) {
        clientService.save(client);
        return "redirect:/admin/clients";
    }

    @RequestMapping(value="/admin/clients/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
        return "redirect:/admin/clients";
    }
}
