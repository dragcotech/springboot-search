package com.mpfleet.admin.services;

import com.mpfleet.admin.models.Supplier;
import com.mpfleet.admin.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> findAll(){
        return supplierRepository.findAll();
    }

    public Page<Supplier> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber -1, 10);
        return supplierRepository.findAll(pageable);
    }

    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }

    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public List<Supplier> findByKeyword(String keyword){
        return supplierRepository.findByKeyword(keyword);
    }
}
