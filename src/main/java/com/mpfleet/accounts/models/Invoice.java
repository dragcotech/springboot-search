package com.mpfleet.accounts.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mpfleet.admin.models.Client;
import com.mpfleet.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {

	private LocalDate invoiceDate;
	private String remarks;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="invoice_status_id")
	private InvoiceStatus invoiceStatus;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="client_id")
	private Client client;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Invoice invoice = (Invoice) o;
		return getId() != null && Objects.equals(getId(), invoice.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
