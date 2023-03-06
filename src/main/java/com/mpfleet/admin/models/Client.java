package com.mpfleet.admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends BaseEntity{

	private String name;
	private String address;
	private String city;
	private String phone;
	private String mobile;
	private String website;
	private String email;
	
	@ManyToOne
	@JoinColumn(name="country_id", insertable=false, updatable=false)
	private Country country;
	
	@ManyToOne
	@JoinColumn(name="state_id", insertable=false, updatable=false)
	private State state;
	
	private String details;
}
