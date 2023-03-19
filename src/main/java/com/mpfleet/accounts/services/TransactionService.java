package com.mpfleet.accounts.services;

import com.mpfleet.accounts.models.Transaction;
import com.mpfleet.accounts.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

}
