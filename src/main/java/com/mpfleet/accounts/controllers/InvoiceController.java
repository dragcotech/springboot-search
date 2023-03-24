package com.mpfleet.accounts.controllers;

import com.mpfleet.accounts.models.Invoice;
import com.mpfleet.accounts.services.InvoiceService;
import com.mpfleet.accounts.services.InvoiceStatusService;
import com.mpfleet.admin.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


	@GetMapping("/accounts/invoices")
	public String getAllPages(Model model, String keyword){
		return getOnePage(model, 1, keyword);
	}

	@GetMapping("/accounts/invoices/page/{pageNumber}")
	public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
		Page<Invoice> page = invoiceService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		page.getContent();
		List<Invoice> invoices;
		invoices = keyword == null? invoiceService.findPage(currentPage).getContent():invoiceService.findByKeyword(keyword);

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("invoices", invoices);

		return "accounts/invoice/invoices";
	}

	@GetMapping("/accounts/addinvoice")
	public String addInvoice(Model model){
		addModelAttributes(model);
		return "accounts/invoice/addInvoice";
	}

	@GetMapping("/accounts/invoice/{op}/{id}")
	public String editInvoice(@PathVariable Long id, @PathVariable String op, Model model){
		Invoice invoice = invoiceService.findById(id);
		model.addAttribute("invoice", invoice);
		addModelAttributes(model);
		return "/accounts/invoice/"+ op + "Invoice";
	}

	@PostMapping("/accounts/invoices")
	public String save(Invoice invoice){
		invoiceService.save(invoice);
		return "redirect:/accounts/invoices";
	}

	@RequestMapping(value = "/accounts/invoices/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public  String delete(@PathVariable Long id){
		invoiceService.delete(id);
		return "redirect:/accounts/invoices";
	}
}
