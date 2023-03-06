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
public class Location extends BaseEntity{
	
	private String description;
	private String details;
	
	@ManyToOne
	@JoinColumn(name="country_id", insertable=false, updatable=false)
	private Country country;
	
	@ManyToOne
	@JoinColumn(name="state_id", insertable=false, updatable=false)
	private State state;
		
	private String city;
	private String address;
}
