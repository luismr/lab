package com.luismachadoreis.tickets.web.controller;

import com.luismachadoreis.tickets.web.model.Ticket;
import com.luismachadoreis.tickets.web.model.TicketStatus;
import com.luismachadoreis.tickets.web.model.User;
import com.luismachadoreis.tickets.web.repo.TicketRepository;
import com.luismachadoreis.tickets.web.repo.UserRepository;
import com.luismachadoreis.tickets.web.security.CurrentUserDetails;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Luis Machado Reis
 */
@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    protected static final String VIEW_NAME_HOME = "index";

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showHomePage(Model model, Principal principal) {
        LOGGER.debug("Opening home...");

        DateTime date = new DateTime();
        date.minusWeeks(1);

        User user = userRepository.findByEmail(principal.getName());

        List<Ticket> tickets = ticketRepository.findByUserAndNotificationDateGreaterThan(user, date);
        model.addAttribute("tickets", tickets);

        return VIEW_NAME_HOME;
    }
}
