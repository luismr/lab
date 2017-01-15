package br.com.singularideas.labs.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by luismr on 11/01/17.
 */
@Controller
public class DashboardController {

    @RequestMapping("/app")
    public String welcome(Map<String, Object> model) {
        return "index";
    }

}
