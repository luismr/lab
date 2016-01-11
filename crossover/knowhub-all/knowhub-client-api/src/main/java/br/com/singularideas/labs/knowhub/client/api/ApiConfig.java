package br.com.singularideas.labs.knowhub.client.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
@ComponentScan({ "br.com.singularideas.labs.knowhub.client.api" })
public class ApiConfig {
	
	@Bean
	@Scope("prototype")
	public Gson gson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson;
	}
	
}
