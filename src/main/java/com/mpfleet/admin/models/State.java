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
public class State extends BaseEntity{

	private String capital;
    private String code;

	@ManyToOne
	private Country country;

	private String details;
}
