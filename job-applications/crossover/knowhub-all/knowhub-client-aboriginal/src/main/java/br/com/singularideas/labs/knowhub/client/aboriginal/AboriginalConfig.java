package br.com.singularideas.labs.knowhub.client.aboriginal;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.singularideas.labs.knowhub.client.api.ApiConfig;

@Configuration
@Import({ApiConfig.class})
@ComponentScan( {"br.com.singularideas.labs.knowhub.client.aboriginal.services"} )
public class AboriginalConfig {
	
	@Bean(name="cacheService")
	public Map<String, Object> cache() {
		return new HashMap<String, Object>();
	}
	
}
