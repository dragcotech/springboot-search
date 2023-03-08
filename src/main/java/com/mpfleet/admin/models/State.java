package com.mpfleet.admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class State extends BaseEntity{

	private String capital;
    private String code;

	@ManyToOne
	private Country country;

	private String details;
}
