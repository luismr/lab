package com.luismachadoreis.tickets.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.luismachadoreis.tickets.web.UnitTestContext;
import com.luismachadoreis.tickets.web.config.WebAppContext;

/**
 * @author Luis Machado Reis
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppContext.class, UnitTestContext.class})
@WebAppConfiguration
public class SignUpControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webAppContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
                .build();
    }

    @Test
    public void redirectRequestToRegistrationPage_ShouldRedirectToRegistrationPage() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/user/register"));
    }
}
