package com.mpfleet.tickets.controllers;

import com.mpfleet.interceptor.annotations.PageTitle;
import com.mpfleet.tickets.models.TicketStatus;
import com.mpfleet.tickets.services.TicketStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TicketStatusController {

	private final TicketStatusService ticketStatusService;

	@Autowired
	public TicketStatusController(TicketStatusService ticketStatusService) {
		this.ticketStatusService = ticketStatusService;
	}

	@GetMapping("/ticket/ticketstatuses")
	@PageTitle("Ticket Statuses")
	public String findAll(Model model){		
		model.addAttribute("ticketStatuses", ticketStatusService.findAll());
		return "/tickets/ticketstatus/ticketStatuses";
	}	
	
	@RequestMapping("/ticket/ticketstatus/{id}")
	@ResponseBody
	public Optional<TicketStatus> findById(@PathVariable Long id) {
		return ticketStatusService.findById(id);
	}

	@PostMapping(value="/ticket/ticketstatuses")
	public String addNew(TicketStatus ticketStatus) {
		ticketStatusService.save(ticketStatus);
		return "redirect:/ticket/ticketstatuses";
	}
	
	@RequestMapping(value="/ticket/ticketstatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		ticketStatusService.delete(id);
		return "redirect:/ticket/ticketstatuses";
	}
}
