package com.mpfleet.hr.services;

import com.mpfleet.hr.models.EmployeeStatus;
import com.mpfleet.hr.repositories.EmployeeStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeStatusService {

    private final EmployeeStatusRepository employeeStatusRepository;

    @Autowired
    public EmployeeStatusService(EmployeeStatusRepository employeeStatusRepository) {
        this.employeeStatusRepository = employeeStatusRepository;
    }

    public List<EmployeeStatus> findAll(){
        return employeeStatusRepository.findAll();
    }

    public Optional<EmployeeStatus> findById(Long id) {
        return employeeStatusRepository.findById(id);
    }

    public void delete(Long id) {
        employeeStatusRepository.deleteById(id);
    }

    public void save(EmployeeStatus employeeStatus) {
        employeeStatusRepository.save(employeeStatus);
    }
}
