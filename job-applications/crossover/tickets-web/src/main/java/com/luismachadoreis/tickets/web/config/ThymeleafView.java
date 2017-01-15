package com.luismachadoreis.tickets.web.config;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * @author Luis Machado Reis
 */
@Configuration
public class ThymeleafView {

	@Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        Set<IDialect> dialects = new HashSet<IDialect>();
        dialects.add(new SpringSecurityDialect());
        dialects.add(new LayoutDialect());
        engine.setAdditionalDialects(dialects);
        LinkedHashSet<ITemplateResolver> templateResolvers = new LinkedHashSet<ITemplateResolver>(2);
        templateResolvers.add(templateResolverServlet());
        templateResolvers.add(layoutTemplateResolverServlet());
        engine.setTemplateResolvers(templateResolvers);
        
        return engine;
    }

    @Bean
    public ServletContextTemplateResolver layoutTemplateResolverServlet() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/layout/");
        templateResolver.setSuffix("");
        templateResolver.setTemplateMode("LEGACYHTML5");
        templateResolver.setOrder(1);
        templateResolver.setCacheable(false);
        
        return templateResolver;
    }
    
    @Bean
    public ServletContextTemplateResolver templateResolverServlet() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/html/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("LEGACYHTML5");
        templateResolver.setOrder(2);
        templateResolver.setCacheable(false);
        
        return templateResolver;
    }

    @Bean
    public ViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setOrder(1);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCache(false);
        
        return resolver;
    }
    
    @Bean
    public DefaultWebSecurityExpressionHandler securityExpressionHandler() {
    	return new DefaultWebSecurityExpressionHandler();
    }

}
