package com.mpfleet.hr.services;


import com.mpfleet.hr.models.EmployeeType;
import com.mpfleet.hr.repositories.EmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeTypeService {

	private final EmployeeTypeRepository employeeTypeRepository;

	@Autowired
	public EmployeeTypeService(EmployeeTypeRepository employeeTypeRepository) {
		this.employeeTypeRepository = employeeTypeRepository;
	}

	public List<EmployeeType> findAll(){
		return employeeTypeRepository.findAll();
	}	

	public Optional<EmployeeType> findById(Long id) {
		return employeeTypeRepository.findById(id);
	}	

	public void delete(Long id) {
		employeeTypeRepository.deleteById(id);
	}

	public void save(EmployeeType employeeType) {
		employeeTypeRepository.save(employeeType);
	}

}
