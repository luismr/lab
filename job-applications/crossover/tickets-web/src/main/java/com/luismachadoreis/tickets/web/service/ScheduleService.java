package com.luismachadoreis.tickets.web.service;

import com.luismachadoreis.tickets.web.mail.HtmlMessage;
import com.luismachadoreis.tickets.web.model.Ticket;
import com.luismachadoreis.tickets.web.model.TicketStatus;
import com.luismachadoreis.tickets.web.repo.TicketRepository;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Machado Reis
 */
@Service
@EnableScheduling
public class ScheduleService {

    private static final int HALF_DAY_IN_HOURS = 12;
    @Autowired
    private TicketRepository ticketRepository;

    @Scheduled(cron="00 */3 * * * *")
    public void sendPendingTicketsToPay() {
        List<Ticket> tickets = ticketRepository.findByStatus(TicketStatus.PENDING);
        tickets = filterTicketByNotificationGraceTime(tickets, HALF_DAY_IN_HOURS);
    }

    @Scheduled(cron="00 */3 * * * *")
    public void sendPayedTicketsConfirmation() {

    }

    @Scheduled(cron="00 00 */4 * * *")
    public void sendYourFlightIsSoon() {

    }

    @Scheduled(cron="00 00 */1 * * *")
    public void sendConfirmedFlightStatusToStaff() {

    }



    private List<Ticket> filterTicketByNotificationGraceTime(List<Ticket> tickets, int expireHours) {
        List<Ticket> ellectables = new ArrayList<>();

        if (tickets != null) {
            DateTime now = new DateTime();

            for (Ticket ticket : tickets) {
                Period p = new Period(ticket.getNotificationDate(), now);
                if (p.getHours() >= expireHours) {
                    ellectables.add(ticket);
                }
            }
        }

        return ellectables;
    }

}
