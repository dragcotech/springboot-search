package com.mpfleet.tickets.services;

import com.mpfleet.tickets.models.TicketStatus;
import com.mpfleet.tickets.repositories.TicketStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketStatusService {

	private final TicketStatusRepository ticketStatusRepository;

	@Autowired
	public TicketStatusService(TicketStatusRepository ticketStatusRepository) {
		this.ticketStatusRepository = ticketStatusRepository;
	}

	public List<TicketStatus> findAll(){
		return ticketStatusRepository.findAll();
	}	

	public Optional<TicketStatus> findById(Long id) {
		return ticketStatusRepository.findById(id);
	}	

	public void delete(Long id) {
		ticketStatusRepository.deleteById(id);
	}

	public void save( TicketStatus ticketStatus) {
		ticketStatusRepository.save(ticketStatus);
	}
}
