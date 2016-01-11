package br.com.singularideas.labs.knowhub.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	
	@RequestMapping("/hello")
    public String hello(final Model model) {
        model.addAttribute("greeting", "Hello! <b>knowhub-api-rest</b>");
        return "/helloworld";
    }
	
}
