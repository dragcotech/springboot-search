package com.mpfleet.tickets.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mpfleet.admin.models.Client;
import com.mpfleet.commons.BaseEntity;
import com.mpfleet.hr.models.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "tickets")
public class Ticket extends BaseEntity {

	private String subject;
	private String details;
	private LocalDate openDate;
	private LocalDate closeDate;
	private String remarks;
	
	@ManyToOne
	@JoinColumn(name="ticket_status_id")
	private TicketStatus ticketStatus;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client raisedBy;

	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee assignedTo;
}
