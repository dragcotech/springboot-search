package com.mpfleet.admin.models;

import com.mpfleet.commons.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
public class Location extends BaseEntity {
	
	private String description;
	private String details;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Country country;

	@ManyToOne(fetch = FetchType.EAGER)
	private State state;

	private String address;
}
