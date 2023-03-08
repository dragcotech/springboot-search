
package com.mpfleet.admin.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Country extends BaseEntity{

	private String code;
	private String name;
	private String description;
	private String nationality;
	private String continent;
	
	@OneToMany(mappedBy="country", fetch = FetchType.EAGER)
	private List<State> states;
}
