package org.secretaria.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SecretariaServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("/ctx-secretaria-backend.xml", "/ctx-secretaria-server.xml")
				.registerShutdownHook();
	}

}
