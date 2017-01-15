package com.luismachadoreis.flyaway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TicketsApplication.class)
@WebAppConfiguration
public class TicketsAppApplicationTests {

	@Test
	public void contextLoads() {
	}

}
