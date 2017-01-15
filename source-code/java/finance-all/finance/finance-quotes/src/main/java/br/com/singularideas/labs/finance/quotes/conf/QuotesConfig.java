package br.com.singularideas.labs.finance.quotes.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.singularideas.labs.finance.utils.conf.UtilsConfig;

@Configuration
@Import({UtilsConfig.class})
@ComponentScan( {"br.com.singularideas.labs.finance.quotes"} )
public class QuotesConfig {

}
