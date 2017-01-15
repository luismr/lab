package br.com.singularideas.labs.knowhub.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value = {"/", "/index", "/index.html", "/index.htm"}, method=RequestMethod.GET)
    public String hello(final Model model) {
        return "/home";
    }
	
}
