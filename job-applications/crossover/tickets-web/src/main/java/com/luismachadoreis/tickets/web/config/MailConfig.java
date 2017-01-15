package com.luismachadoreis.tickets.web.config;

import com.luismachadoreis.tickets.web.mail.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("com.luismachadoreis.tickets.web")
@PropertySource("classpath:application.properties")
public class MailConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public Host host() {
		String hostname = env.getProperty("tickets.smtp.fqdn");
		Integer port = Integer.valueOf(env.getProperty("tickets.smtp.port"));

		String username = env.getProperty("tickets.smtp.username");
		String password = env.getProperty("tickets.smtp.password");
		String defaultFrom = env.getProperty("tickets.smtp.username");

		Boolean ssl = Boolean.valueOf(env.getProperty("tickets.smtp.ssl"));

		Host host = new Host();
		host.setFqdn(hostname);
		host.setPort(port);
		host.setUsername(username);
		host.setPassword(password);
		host.setSsl(ssl);
		host.setDefaultFrom(defaultFrom);
		
		return host;
	}

}
