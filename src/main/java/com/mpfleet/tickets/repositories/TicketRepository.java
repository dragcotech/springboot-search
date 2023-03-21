package com.mpfleet.tickets.repositories;

import com.mpfleet.tickets.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT t FROM Ticket t WHERE t.subject LIKE %?1% OR t.details LIKE %?1%" +
            "OR t.assignedTo.fullName LIKE %?1% OR t.assignedTo.firstName LIKE %?1% OR t.assignedTo.lastName LIKE %?1%" +
            "OR t.raisedBy.name LIKE %?1% OR t.raisedBy.email LIKE %?1% OR t.ticketStatus.description LIKE %?1%")
    List<Ticket> findByKeyword(String keyword);
}
