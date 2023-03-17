package com.mpfleet.fleet.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mpfleet.admin.models.Location;
import com.mpfleet.hr.models.Employee;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntity{

	private String name;
	private String vehicleNumber;
	private String description;
	private String power;
	private String fuelCapacity;
	private String netWeight;

	private LocalDate registrationDate;

	private LocalDate acquisitionDate;

	@ManyToOne
	@JoinColumn(name="vehicle_type_id")
	private VehicleType vehicleType;

	@ManyToOne
	@JoinColumn(name="vehicle_make_id")
	private VehicleMake vehicleMake;

	@ManyToOne
	@JoinColumn(name="vehicle_status_id")
	private VehicleStatus vehicleStatus;

	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee inCharge;

	@ManyToOne
	@JoinColumn(name="vehicle_model_id")
	private VehicleModel vehicleModel;	

	@ManyToOne
	@JoinColumn(name="location_id")
	private Location currentLocation;
}
