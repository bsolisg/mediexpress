package com.mediexpress.soporte.repository;

import com.mediexpress.soporte.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
