package com.mpfleet.accounts.services;

import com.mpfleet.accounts.models.Invoice;
import com.mpfleet.accounts.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

	private final InvoiceRepository invoiceRepository;

	@Autowired
	public InvoiceService(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	public List<Invoice> findAll(){
		return invoiceRepository.findAll();
	}	

	public Invoice findById(Long id) {
		return invoiceRepository.findById(id).orElse(null);
	}	

	public void delete(Long id) {
		invoiceRepository.deleteById(id);
	}

	public void save(Invoice invoice) {
		invoiceRepository.save(invoice);
	}

	public List<Invoice> findByKeyword(String keyword){
		return invoiceRepository.findByKeyword(keyword);
	}
}
