package com.mpfleet.accounts.services;

import com.mpfleet.accounts.models.InvoiceStatus;
import com.mpfleet.accounts.repositories.InvoiceStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceStatusService {

	private final InvoiceStatusRepository invoiceStatusRepository;

	@Autowired
	public InvoiceStatusService(InvoiceStatusRepository invoiceStatusRepository) {
		this.invoiceStatusRepository = invoiceStatusRepository;
	}

	public List<InvoiceStatus> findAll(){
		return invoiceStatusRepository.findAll();
	}	

	public Optional<InvoiceStatus> findById(Long id) {
		return invoiceStatusRepository.findById(id);
	}	

	public void delete(Long id) {
		invoiceStatusRepository.deleteById(id);
	}

	public void save(InvoiceStatus invoiceStatus) {
		invoiceStatusRepository.save(invoiceStatus);
	}
}
