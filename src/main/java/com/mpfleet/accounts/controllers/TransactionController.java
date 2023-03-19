package com.mpfleet.accounts.controllers;

import com.mpfleet.accounts.models.Transaction;
import com.mpfleet.accounts.services.TransactionService;
import com.mpfleet.accounts.services.TransactionStatusService;
import com.mpfleet.accounts.services.TransactionTypeService;
import com.mpfleet.admin.services.ClientService;
import com.mpfleet.admin.services.ContactService;
import com.mpfleet.admin.services.SupplierService;
import com.mpfleet.hr.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionStatusService transactionStatusService;
    private final TransactionTypeService transactionTypeService;
    private final ContactService contactService;
    private final SupplierService supplierService;
    private final ClientService clientService;
    private final EmployeeService employeeService;

    @Autowired
    public TransactionController(TransactionService transactionService, TransactionStatusService transactionStatusService,
                                 TransactionTypeService transactionTypeService, ContactService contactService,
                                 SupplierService supplierService, ClientService clientService,
                                 EmployeeService employeeService) {
        this.transactionService = transactionService;
        this.transactionStatusService = transactionStatusService;
        this.transactionTypeService = transactionTypeService;
        this.contactService = contactService;
        this.supplierService = supplierService;
        this.clientService = clientService;
        this.employeeService = employeeService;
    }

    public void addModelAttributes(Model model){
        model.addAttribute("transactionStatuses", transactionStatusService.findAll());
        model.addAttribute("transactionTypes", transactionTypeService.findAll());
        model.addAttribute("contacts", contactService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("employees", employeeService.findAll());
    }

    @GetMapping("/transactions")
    public String getAll(Model model){
        List<Transaction> transactions = transactionService.findAll();
        model.addAttribute("transactions", transactions);
        addModelAttributes(model);
        return "/accounts/transaction/transactions";
    }

    @GetMapping("/addtransaction")
    public String addTransaction(Model model){
        addModelAttributes(model);
        return "/accounts/transaction/addTransaction";
    }

    @GetMapping("/transactions/{op}/{id}")
    public String editTransaction(@PathVariable Long id, @PathVariable String op, Model model){
        Transaction transaction = transactionService.findById(id);
        model.addAttribute("transaction", transaction);
        addModelAttributes(model);
        return "/accounts/transaction/"+ op + "Transaction";
    }

    @PostMapping("/transactions")
    public String save(Transaction transaction){
        transactionService.save(transaction);
        return "redirect:/transactions";
    }

    @RequestMapping(value = "/transactions/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Long id){
        transactionService.delete(id);
        return "redirect:/transactions";
    }
}
