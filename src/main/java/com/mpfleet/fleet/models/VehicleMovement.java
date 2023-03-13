package com.mpfleet.fleet.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mpfleet.admin.models.Location;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "vehicle_movement")
public class VehicleMovement extends BaseEntity{

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date firstDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date secondDate;

	private String remarks;

	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name="location_id_f")
	private Location firstLocation;

	@ManyToOne
	@JoinColumn(name="location_id_s")
	private Location secondLocation;
}
