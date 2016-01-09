package br.com.singularideas.labs.knowhub.client.aboriginal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppleScriptService {

	private static final String APPLESCRIPT_CMD = "osascript";
	private static final String APPLESCRIPT_ARG_EXEC = "-e";
	
	@Autowired
	private RuntimeService runtime;
	
	public String execute(final String stuff) {
		return runtime.perform(APPLESCRIPT_CMD, new String [] {APPLESCRIPT_ARG_EXEC, stuff});
	}
	
}
