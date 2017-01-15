package com.luismachadoreis.tickets.web.controller;

import com.luismachadoreis.tickets.web.model.Ticket;
import com.luismachadoreis.tickets.web.model.User;
import com.luismachadoreis.tickets.web.repo.TicketRepository;
import com.luismachadoreis.tickets.web.repo.UserRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

/**
 * @author Luis Machado Reis
 */
@Controller
@RequestMapping("/tickets")
public class TicketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);

    protected static final String VIEW_NAME_BUYNOW = "tickets/search";

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String showHomePage(Model model, Principal principal) {
        LOGGER.debug("Opening tickets search ...");
        return VIEW_NAME_BUYNOW;
    }
}
