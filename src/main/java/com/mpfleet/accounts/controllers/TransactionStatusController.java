package com.mpfleet.accounts.controllers;

import com.mpfleet.accounts.models.TransactionStatus;
import com.mpfleet.accounts.services.TransactionStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionStatusController {

    private final TransactionStatusService transactionStatusService;

    @Autowired
    public TransactionStatusController(TransactionStatusService transactionStatusService) {
        this.transactionStatusService = transactionStatusService;
    }

    @GetMapping("/transactionstatuses")
    public String parameters(Model model){
        List<TransactionStatus> transactionStatuses = transactionStatusService.findAll();
        model.addAttribute("transactionStatuses", transactionStatuses);
        return "/accounts/transactionstatus/transactionStatuses";
    }


    @GetMapping("/transactionstatus/{id}")
    @ResponseBody
    public TransactionStatus getById(@PathVariable Long id){
        return transactionStatusService.findById(id).orElse(null);
    }

    @PostMapping("/transactionstatuses")
    public String save(TransactionStatus transactionStatus){
        transactionStatusService.save(transactionStatus);
        return "redirect:/transactionstatuses";
    }

    @RequestMapping(value="/transactionstatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Long id) {
        transactionStatusService.delete(id);
        return "redirect:/transactionstatuses";
    }
}
