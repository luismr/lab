package br.com.singularideas.labs.knowhub.client.aboriginal.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProtectionMonitorService {

	@Autowired
	private ProtectManagerService protectManagerService;
	
	private ProtectManager protectManager;
	
	@PostConstruct
	public void start() {
		protectManager = protectManagerService.getManager();
		protectManager.protectMe();
	}
	
	@PreDestroy
	public void stop() {
		protectManager.unprotectMe();
	}
	
}
