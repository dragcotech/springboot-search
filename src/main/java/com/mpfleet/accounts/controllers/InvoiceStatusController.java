package com.mpfleet.accounts.controllers;

import com.mpfleet.accounts.models.InvoiceStatus;
import com.mpfleet.accounts.services.InvoiceStatusService;
import com.mpfleet.interceptor.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class InvoiceStatusController {

	private final InvoiceStatusService invoiceStatusService;

	@Autowired
	public InvoiceStatusController(InvoiceStatusService invoiceStatusService) {
		this.invoiceStatusService = invoiceStatusService;
	}

	@GetMapping("/accounts/invoicestatuses")
	@PageTitle("Invoice Statuses")
	public String findAll(Model model){		
		model.addAttribute("invoiceStatuses", invoiceStatusService.findAll());
		return "/accounts/invoicestatus/invoiceStatuses";
	}	
	
	@RequestMapping("/accounts/invoicestatus/{id}")
	@ResponseBody
	public Optional<InvoiceStatus> findById(@PathVariable Long id) {
		return invoiceStatusService.findById(id);
	}


	@PostMapping(value="/accounts/invoicestatuses")
	public String addNew(InvoiceStatus invoiceStatus) {
		invoiceStatusService.save(invoiceStatus);
		return "redirect:/accounts/invoicestatuses";
	}	

	@RequestMapping(value="/accounts/invoicestatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		invoiceStatusService.delete(id);
		return "redirect:/accounts/invoicestatuses";
	}
}
