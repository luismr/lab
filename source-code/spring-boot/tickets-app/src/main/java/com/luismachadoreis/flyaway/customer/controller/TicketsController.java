package com.luismachadoreis.flyaway.customer.controller;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
@RequestMapping("/customer")
public class TicketsController {

    public static final String FORWARD_HOME = "/customer/home";
    public static final String FORWARD_LOGIN = String.format("redirect:%s", FORWARD_HOME);
    public static final String FORWARD_LOGOUT = "/customer/logout";

    private Twitter twitter;
    private ConnectionRepository connectionRepository;

    @Inject
    public TicketsController(final Twitter twitter, final ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(path = "/", method=RequestMethod.GET)
    public String home(final Model model) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return FORWARD_LOGIN;
        }

        model.addAttribute(twitter.userOperations().getUserProfile());
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        model.addAttribute("friends", friends);
        return FORWARD_HOME;
    }

    @RequestMapping( path = "/logout", method = RequestMethod.GET)
    public String logout() {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return FORWARD_LOGIN;
        }

        return FORWARD_LOGOUT;
    }

}
