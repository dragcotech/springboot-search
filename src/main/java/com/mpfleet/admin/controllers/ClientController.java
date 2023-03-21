package com.mpfleet.admin.controllers;

import com.mpfleet.admin.models.Client;
import com.mpfleet.admin.services.ClientService;
import com.mpfleet.admin.services.CountryService;
import com.mpfleet.admin.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/clients")
    public String findAll(Model model, String keyword){
        List<Client> clients;

        clients = keyword == null? clientService.findAll():clientService.findByKeyword(keyword);

        model.addAttribute("clients", clients);
        return "/admin/client/allClients";
    }

    @GetMapping("/addclient")
    public String addClient(Model model){
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("states", stateService.findAll());
        return "/admin/client/addClient";
    }

    @GetMapping("/client/{op}/{id}")
    public String editClient(@PathVariable Long id, @PathVariable String op, Model model){
        Client client = clientService.findById(id);
        model.addAttribute("client", client);
        addModelAttributes(model);
        return "/admin/client/"+ op + "Client";
    }

    @PostMapping("/clients")
    public String save(Client client) {
        clientService.save(client);
        return "redirect:/clients";
    }

    @RequestMapping(value="/clients/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
        return "redirect:/clients";
    }
}
