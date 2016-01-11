package br.com.singularideas.labs.knowhub.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.singularideas.labs.knowhub.model.ModelConfig;

@Configuration
@Import({ModelConfig.class})
public class ApiRestConfig {

}
