package com.mpfleet.hr.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Employee extends Person {
		
	@ManyToOne
	@JoinColumn(name="employeetype_id", insertable=false, updatable=false)
	private EmployeeType employeeType;

	private String photo;
	private String username;
	
	@ManyToOne
	@JoinColumn(name="jobtitle_id", insertable=false, updatable=false)
	private JobTitle jobTitle;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
}
