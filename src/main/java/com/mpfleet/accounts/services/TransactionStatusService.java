package com.mpfleet.accounts.services;

import com.mpfleet.accounts.models.TransactionStatus;
import com.mpfleet.accounts.repositories.TransactionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionStatusService {

    private final TransactionStatusRepository transactionStatusRepository;

    @Autowired
    public TransactionStatusService(TransactionStatusRepository transactionStatusRepository) {
        this.transactionStatusRepository = transactionStatusRepository;
    }

    public List<TransactionStatus> findAll(){
        return transactionStatusRepository.findAll();
    }

    public Optional<TransactionStatus> findById(Long id) {
        return transactionStatusRepository.findById(id);
    }

    public void delete(Long id) {
        transactionStatusRepository.deleteById(id);
    }

    public void save(TransactionStatus transactionStatus) {
        transactionStatusRepository.save(transactionStatus);
    }
}
