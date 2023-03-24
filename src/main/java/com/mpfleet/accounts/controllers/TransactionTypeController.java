package com.mpfleet.accounts.controllers;

import com.mpfleet.accounts.models.TransactionType;
import com.mpfleet.accounts.services.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionTypeController {
    private final TransactionTypeService transactionTypeService;

    @Autowired
    public TransactionTypeController(TransactionTypeService transactionTypeService) {
        this.transactionTypeService = transactionTypeService;
    }

    @GetMapping("/accounts/transactiontypes")
    public String parameters(Model model){
        List<TransactionType> transactionTypes = transactionTypeService.findAll();
        model.addAttribute("transactionTypes", transactionTypes);
        return "/accounts/transactiontype/transactionTypes";
    }

    @GetMapping("/accounts/transactiontype/{id}")
    @ResponseBody
    public TransactionType getById(@PathVariable Long id){
        return transactionTypeService.findById(id).orElse(null);
    }

    @PostMapping("/accounts/transactiontypes")
    public String addNew(TransactionType transactionType) {
        transactionTypeService.save(transactionType);
        return "redirect:/accounts/transactiontypes";
    }

    @RequestMapping(value="/accounts/transactiontype/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Long id) {
        transactionTypeService.delete(id);
        return "redirect:/accounts/transactiontypes";
    }
}
