package br.com.singularideas.labs.knowhub.client.aboriginal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.singularideas.labs.knowhub.client.aboriginal.AboriginalException;

@Component
public class WindowsProtectManager implements ProtectManager {

	@Autowired
	private ProtectThread thread;

	@Override
	public void protectMe() {
		thread.start();
	}

	@Override
	public void unprotectMe() {
		thread.shutdown();
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
