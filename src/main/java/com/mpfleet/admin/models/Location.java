package com.mpfleet.admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Country country;

	@ManyToOne(fetch = FetchType.EAGER)
	private State state;

	private String address;
}
