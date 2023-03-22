package com.mpfleet.tickets.controllers;

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

	@GetMapping("/ticketstatuses")
	public String findAll(Model model){		
		model.addAttribute("ticketStatuses", ticketStatusService.findAll());
		return "/tickets/ticketstatus/ticketStatuses";
	}	
	
	@RequestMapping("/ticketstatus/{id}")
	@ResponseBody
	public Optional<TicketStatus> findById(@PathVariable Long id) {
		return ticketStatusService.findById(id);
	}

	@PostMapping(value="/ticketstatuses")
	public String addNew(TicketStatus ticketStatus) {
		ticketStatusService.save(ticketStatus);
		return "redirect:/ticketstatuses";
	}
	
	@RequestMapping(value="/ticketstatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		ticketStatusService.delete(id);
		return "redirect:/ticketstatuses";
	}
}