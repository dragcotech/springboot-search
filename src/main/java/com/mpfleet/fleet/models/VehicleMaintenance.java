package com.mpfleet.fleet.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mpfleet.admin.models.Supplier;
import com.mpfleet.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "vehicle_maintenance")
public class VehicleMaintenance extends BaseEntity {

	private String price;

	private LocalDate startDate;

	private LocalDate endDate;

	private String remarks;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
}
