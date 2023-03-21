package com.mpfleet.tickets.services;

import com.mpfleet.tickets.models.Ticket;
import com.mpfleet.tickets.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketService {

	private final TicketRepository ticketRepository;

	@Autowired
	public TicketService(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	public List<Ticket> findAll(){
		return ticketRepository.findAll();
	}

	public Page<Ticket> findPage(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber -1, 10);
		return ticketRepository.findAll(pageable);
	}

	public Ticket findById(Long id) {
		return ticketRepository.findById(id).orElse(null);
	}	

	public void delete(Long id) {
		ticketRepository.deleteById(id);
	}

	public void save(Ticket invoice) {
		ticketRepository.save(invoice);
	}

	public List<Ticket> findByKeyword(String keyword){
		return ticketRepository.findByKeyword(keyword);
	}
}
