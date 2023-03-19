package com.mpfleet.accounts.services;

import com.mpfleet.accounts.models.TransactionType;
import com.mpfleet.accounts.repositories.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;

    @Autowired
    public TransactionTypeService(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    public List<TransactionType> findAll(){
        return transactionTypeRepository.findAll();
    }

    public Optional<TransactionType> findById(Long id) {
        return transactionTypeRepository.findById(id);
    }

    public void delete(Long id) {
        transactionTypeRepository.deleteById(id);
    }

    public void save(TransactionType transactionType) {
        transactionTypeRepository.save(transactionType);
    }
}
