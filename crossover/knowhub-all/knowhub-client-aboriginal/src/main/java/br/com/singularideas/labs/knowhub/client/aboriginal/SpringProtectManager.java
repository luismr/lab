package br.com.singularideas.labs.knowhub.client.aboriginal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class SpringProtectManager {

	protected ApplicationContext ctx = null;
	
	public SpringProtectManager() {
		this.ctx = new AnnotationConfigApplicationContext(AboriginalConfig.class);
	}

}
