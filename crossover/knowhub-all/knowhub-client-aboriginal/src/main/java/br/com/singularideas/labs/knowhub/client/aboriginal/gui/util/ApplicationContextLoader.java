package br.com.singularideas.labs.knowhub.client.aboriginal.gui.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import br.com.singularideas.labs.knowhub.client.aboriginal.AboriginalConfig;

public class ApplicationContextLoader {

	private static ApplicationContextLoader instance;
	
	public static ApplicationContextLoader getInstance() {
		if (instance == null) {
			instance = new ApplicationContextLoader();
		}
		
		return instance;
	}
	
	private ApplicationContext ctx;
	
	private ApplicationContextLoader() {
		super();
		ctx = new AnnotationConfigApplicationContext(AboriginalConfig.class);
	}

	public ApplicationContext getApplicationContext() {
		return ctx;
	}

	public void shutdown() {
		((AbstractApplicationContext)ctx).registerShutdownHook();
	}
}
