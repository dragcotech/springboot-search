package com.mpfleet.fleet.models;

import com.mpfleet.admin.models.Client;
import com.mpfleet.admin.models.Location;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_hire")
public class VehicleHire extends BaseEntity{

	private String price;
	private String remarks;

	private LocalDate dateIn;

	private LocalDate dateOut;

	private String timeOut;
	private String timeIn;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;

}
