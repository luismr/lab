package com.luismachadoreis.tickets.web.mail;

public interface IHtmlMessage extends IMessage {

	String getMessageHtml();

	void setMessageHtml(String messageHtml);

}