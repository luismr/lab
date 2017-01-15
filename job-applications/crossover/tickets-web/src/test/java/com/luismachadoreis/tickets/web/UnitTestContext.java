package com.luismachadoreis.tickets.web;

import static org.mockito.Mockito.mock;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.luismachadoreis.tickets.web.service.UserService;

/**
 * @author Luis Machado Reis
 */
@Configuration
public class UnitTestContext {

    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    @Bean
    @Primary
    public UserService userService() {
        return mock(UserService.class);
    }
    
}
