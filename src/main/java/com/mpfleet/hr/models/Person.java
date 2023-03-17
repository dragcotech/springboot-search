package com.mpfleet.hr.models;

import com.mpfleet.admin.models.BaseEntity;
import com.mpfleet.admin.models.Country;
import com.mpfleet.admin.models.State;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Person extends BaseEntity {

	private String firstName;
	private String lastName;
	private String otherName;

	@Formula(value = " concat(firstname, ' ', lastname) ")
	private String fullName;

	private String title;
	private String initials;
	private String socialSecurityNumber;
	private String gender;
	private String maritalStatus;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;
	
	@ManyToOne
	@JoinColumn(name="state_id")
	private State state;

	private LocalDate dateOfBirth;
	private String address;
	private String phone;
	private String mobile;
	private String email;
	private String photo;
}
