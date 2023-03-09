package com.mpfleet.admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	private String phone;
	private String mobile;
	private String website;
	private String email;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="country_id")
	private Country country;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="state_id")
	private State state;
	
	private String details;
}
