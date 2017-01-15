package com.luismachadoreis.tickets.web.repo;

import com.luismachadoreis.tickets.web.model.Ticket;
import com.luismachadoreis.tickets.web.model.TicketStatus;
import com.luismachadoreis.tickets.web.model.User;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author Luis Machado Reis
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUserAndStatus(User user, TicketStatus status);

    List<Ticket> findByUserAndNotificationDateGreaterThan(User user, DateTime dateTime);

    List<Ticket> findByStatus(TicketStatus status);

}
