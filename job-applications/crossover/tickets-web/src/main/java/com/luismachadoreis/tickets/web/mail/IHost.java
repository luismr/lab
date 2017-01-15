package com.luismachadoreis.tickets.web.mail;

public interface IHost {

	String getName();

	void setName(String name);

	String getFqdn();

	void setFqdn(String fqdn);

	int getPort();

	void setPort(int port);

	String getUsername();

	void setUsername(String username);

	String getPassword();

	void setPassword(String password);

	boolean isSsl();

	void setSsl(boolean ssl);

	String getDefaultFrom();

	void setDefaultFrom(String defaultFrom);

}