package com.mpfleet.tickets.controllers;

import com.mpfleet.admin.services.ClientService;
import com.mpfleet.hr.services.EmployeeService;
import com.mpfleet.tickets.models.Ticket;
import com.mpfleet.tickets.services.TicketService;
import com.mpfleet.tickets.services.TicketStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TicketController {
	
	private final TicketService ticketService;
	private final TicketStatusService ticketStatusService;
	private final ClientService clientService;
	private final EmployeeService employeeService;

	@Autowired
	public TicketController(TicketService ticketService, TicketStatusService ticketStatusService, ClientService clientService, EmployeeService employeeService) {
		this.ticketService = ticketService;
		this.ticketStatusService = ticketStatusService;
		this.clientService = clientService;
		this.employeeService = employeeService;
	}

	public void addModelAttributes(Model model){
		model.addAttribute("tickets", ticketService.findAll());
		model.addAttribute("clients", clientService.findAll());
		model.addAttribute("ticketStatuses", ticketStatusService.findAll());
		model.addAttribute("employees", employeeService.findAll());
	}


	@GetMapping("/alltickets")
	public String getAllPages(Model model, String keyword){
		return getOnePage(model, 1, keyword);
	}

	@GetMapping("/alltickets/page/{pageNumber}")
	public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage, String keyword){
		Page<Ticket> page = ticketService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		long totalItems = page.getTotalElements();
		page.getContent();
		List<Ticket> tickets;
		tickets = keyword == null? ticketService.findPage(currentPage).getContent():ticketService.findByKeyword(keyword);

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("tickets", tickets);

		return "tickets/ticket/tickets";
	}

	@GetMapping("/addticket")
	public String addTicket(Model model){
		addModelAttributes(model);
		return "/tickets/ticket/addTicket";
	}
	
	@RequestMapping("/tickets/findById")
	@ResponseBody
	public Ticket findById(Long id) {
		return ticketService.findById(id);
	}

	@GetMapping("/ticket/{op}/{id}")
	public String editTicket(@PathVariable Long id, @PathVariable String op, Model model){
		Ticket ticket = ticketService.findById(id);
		model.addAttribute("ticket", ticket);
		addModelAttributes(model);
		return "/tickets/ticket/"+ op + "Ticket";
	}

	@PostMapping("/alltickets")
	public String save(Ticket ticket){
		ticketService.save(ticket);
		return "redirect:/alltickets";
	}
	
	@RequestMapping(value="/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Long id) {
		ticketService.delete(id);
		return "redirect:/alltickets";
	}
}
