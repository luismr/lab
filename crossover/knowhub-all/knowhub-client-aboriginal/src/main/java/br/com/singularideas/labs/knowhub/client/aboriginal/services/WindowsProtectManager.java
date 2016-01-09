package br.com.singularideas.labs.knowhub.client.aboriginal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.singularideas.labs.knowhub.client.aboriginal.AboriginalException;
import br.com.singularideas.labs.knowhub.client.aboriginal.ProtectManager;

@Component
public class WindowsProtectManager implements ProtectManager {

	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private ProtectThread thread;

	@Override
	public void protectMe() {
		throw new AboriginalException("Not implemented yet!");
	}

	@Override
	public void unprotectMe() {
		throw new AboriginalException("Not implemented yet!");
	}

	@Override
	public String getScreenShotFolder() {
		throw new AboriginalException("Not implemented yet!");
	}

	@Override
	public String getOldScreenShotFolder() {
		throw new AboriginalException("Not implemented yet!");
	}

}
