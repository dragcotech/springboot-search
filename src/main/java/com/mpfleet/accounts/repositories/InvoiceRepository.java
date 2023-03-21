package com.mpfleet.accounts.repositories;

import com.mpfleet.accounts.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(value = "SELECT i FROM Invoice i WHERE i.client.name LIKE %?1% OR i.invoiceStatus.description LIKE %?1%")
    List<Invoice> findByKeyword(String keyword);
}
