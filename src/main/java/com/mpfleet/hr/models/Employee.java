package com.mpfleet.hr.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "employees")
public class Employee extends Person {

	private String photo;
	private String username;

	private LocalDate hireDate;
		
	@ManyToOne
	@JoinColumn(name="employee_type_id")
	private EmployeeType employeeType;
	
	@ManyToOne
	@JoinColumn(name="job_title_id")
	private JobTitle jobTitle;
}
