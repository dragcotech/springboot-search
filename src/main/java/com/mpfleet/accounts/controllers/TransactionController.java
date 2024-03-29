package com.mpfleet.accounts.controllers;

import com.mpfleet.accounts.models.Transaction;
import com.mpfleet.accounts.services.TransactionService;
import com.mpfleet.accounts.services.TransactionStatusService;
import com.mpfleet.accounts.services.TransactionTypeService;
import com.mpfleet.admin.services.ClientService;
import com.mpfleet.admin.services.ContactService;
import com.mpfleet.admin.services.SupplierService;
import com.mpfleet.hr.services.EmployeeService;
import com.mpfleet.interceptor.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/accounts/transactions")
    @PageTitle("Transactions")
    public String getAllPages(Model model, String keyword){
        return getOnePage(model, 1, keyword);
    }

    @GetMapping("/accounts/transactions/page/{pageNumber}")
    @PageTitle("Transactions")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
        Page<Transaction> page = transactionService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        page.getContent();
        List<Transaction> transactions;
        transactions = keyword == null? transactionService.findPage(currentPage).getContent():transactionService.findByKeyword(keyword);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("transactions", transactions);

        return "accounts/transaction/transactions";
    }

    @GetMapping("/accounts/addtransaction")
    @PageTitle("Add Transactions")
    public String addTransaction(Model model){
        addModelAttributes(model);
        return "/accounts/transaction/addTransaction";
    }

    @GetMapping("/accounts/transactions/{op}/{id}")
    @PageTitle("Edit/Details Transaction")
    public String editTransaction(@PathVariable Long id, @PathVariable String op, Model model){
        Transaction transaction = transactionService.findById(id);
        model.addAttribute("transaction", transaction);
        addModelAttributes(model);
        return "/accounts/transaction/"+ op + "Transaction";
    }

    @PostMapping("/accounts/transactions")
    public String save(Transaction transaction){
        transactionService.save(transaction);
        return "redirect:/accounts/transactions";
    }

    @RequestMapping(value = "/accounts/transactions/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Long id){
        transactionService.delete(id);
        return "redirect:/accounts/transactions";
    }
}
