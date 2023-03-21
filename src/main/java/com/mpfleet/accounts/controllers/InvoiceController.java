package com.mpfleet.accounts.controllers;

import com.mpfleet.accounts.models.Invoice;
import com.mpfleet.accounts.services.InvoiceService;
import com.mpfleet.accounts.services.InvoiceStatusService;
import com.mpfleet.admin.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class InvoiceController {
	
	private final InvoiceService invoiceService;
	private final ClientService clientService;
	private final InvoiceStatusService invoiceStatusService;

	@Autowired
	public InvoiceController(InvoiceService invoiceService, ClientService clientService, InvoiceStatusService invoiceStatusService) {
		this.invoiceService = invoiceService;
		this.clientService = clientService;
		this.invoiceStatusService = invoiceStatusService;
	}

	public void addModelAttributes(Model model){
		model.addAttribute("invoiceStatuses", invoiceStatusService.findAll());
		model.addAttribute("clients", clientService.findAll());
	}


	@GetMapping("/invoices")
	public String getAll(Model model, String keyword){
		List<Invoice> invoices;

		invoices = keyword == null? invoiceService.findAll():invoiceService.findByKeyword(keyword);

		model.addAttribute("invoices", invoices);
		addModelAttributes(model);
		return "/accounts/invoice/invoices";
	}

	@GetMapping("/addinvoice")
	public String addInvoice(Model model){
		addModelAttributes(model);
		return "accounts/invoice/addInvoice";
	}

	@GetMapping("/invoice/{op}/{id}")
	public String editInvoice(@PathVariable Long id, @PathVariable String op, Model model){
		Invoice invoice = invoiceService.findById(id);
		model.addAttribute("invoice", invoice);
		addModelAttributes(model);
		return "/accounts/invoice/"+ op + "Invoice";
	}

	@PostMapping("/invoices")
	public String save(Invoice invoice){
		invoiceService.save(invoice);
		return "redirect:/invoices";
	}

	@RequestMapping(value = "/invoices/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public  String delete(@PathVariable Long id){
		invoiceService.delete(id);
		return "redirect:/invoices";
	}
}
