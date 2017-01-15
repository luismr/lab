package com.luismachadoreis.tickets.web.mail;

public interface IMessage {

	String getTo();

	void setTo(String to);

	String getFrom();

	void setFrom(String from);

	String getSubject();

	void setSubject(String subject);

	String getMessage();

	void setMessage(String message);

	IAttachment getAttachment();

	void setAttachment(IAttachment attachment);

	IHost getHost();

	void setHost(IHost host);

	String getName();

	void setName(String name);

}