
package com.mpfleet.admin.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country extends BaseEntity{

	private String code;
	private String name;
	private String description;
	private String nationality;
	private String continent;
	
	@OneToMany(mappedBy="country", fetch = FetchType.EAGER)
	private List<State> states;
}
