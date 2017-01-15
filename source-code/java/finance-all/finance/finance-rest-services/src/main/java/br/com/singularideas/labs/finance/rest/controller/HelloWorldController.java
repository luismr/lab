package br.com.singularideas.labs.finance.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	
	@RequestMapping("/hello")
    public String hello(final Model model) {
        model.addAttribute("greeting", "Hello finance-rest-services");
        return "/helloworld";
    }
	
}
