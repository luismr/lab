package br.com.singularideas.labs.finance.rest.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.singularideas.labs.finance.quotes.conf.QuotesConfig;
import br.com.singularideas.labs.finance.utils.conf.UtilsConfig;

@Configuration
@Import({UtilsConfig.class, QuotesConfig.class})
public class RestServicesConfig {

}
