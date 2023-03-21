package com.mpfleet.accounts.repositories;

import com.mpfleet.accounts.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT t FROM Transaction t WHERE t.client.name LIKE %?1% OR t.client.email LIKE %?1%" +
            "OR t.transactionStatus.description LIKE %?1% OR t.transactionType.description LIKE %?1%" +
            "OR t.contact.email LIKE %?1% OR t.contact.firstname LIKE %?1% OR t.contact.lastname LIKE %?1%" +
            "OR t.supplier.name LIKE %?1% OR t.supplier.email LIKE %?1% OR t.supplier.website LIKE %?1%" +
            "OR t.approvedBy.fullName LIKE %?1% OR t.approvedBy.firstName LIKE %?1% OR t.approvedBy.email LIKE %?1%")
    List<Transaction> findByKeyword(String keyword);
}
