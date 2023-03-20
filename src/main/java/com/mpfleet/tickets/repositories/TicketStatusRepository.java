package com.mpfleet.tickets.repositories;

import com.mpfleet.tickets.models.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketStatusRepository extends JpaRepository<TicketStatus, Long> {

}
