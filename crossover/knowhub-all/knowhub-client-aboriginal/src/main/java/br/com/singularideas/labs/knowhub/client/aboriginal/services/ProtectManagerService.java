package br.com.singularideas.labs.knowhub.client.aboriginal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProtectManagerService {

	@Autowired
	private RuntimeService runtime;
	
	@Autowired
	private MacProtectManager mac;
	
	@Autowired
	private WindowsProtectManager windows;
	
	public ProtectManager getManager() {
		ProtectManager pm = null;

		String os = runtime.getOperationalSystem();
		switch (os) {
		case OperationalSystem.MAC:
			pm = mac;
			break;

		case OperationalSystem.WINDOWS:
			pm = windows;
			break;
		}

		return pm;
	}

}
