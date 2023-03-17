package com.mpfleet.hr.services;

import com.mpfleet.hr.models.Employee;
import com.mpfleet.hr.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}	

	public Employee findById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}	

	public void delete(Long id) {
		employeeRepository.deleteById(id);
	}

	public void save( Employee employee) {
		employeeRepository.save(employee);
	}

	public Employee findByUsername(String un) {
		return employeeRepository.findByUsername(un);
	}

}
