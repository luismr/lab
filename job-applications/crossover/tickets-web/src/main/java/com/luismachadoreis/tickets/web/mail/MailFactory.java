package com.luismachadoreis.tickets.web.mail;

import org.apache.commons.mail.*;
import org.apache.commons.validator.routines.EmailValidator;

public class MailFactory {

	public static Email create(final IMessage message) throws EmailException {
		if (message == null) {
			throw new IllegalArgumentException("message cannot be null!");
		} else if (message.getTo() == null || ! EmailValidator.getInstance().isValid(message.getTo())) {
			throw new IllegalArgumentException(String.format("Email [%s] is not valid!", message.getTo()));
		} else if (message.getHost() == null || message.getHost().getFqdn() == null || message.getHost().getPort() <= 0 || message.getHost().getPort() >= 65535) {
			throw new IllegalArgumentException(String.format("host is not valid or null [%s]", message.getHost()));
		} else if (message.getSubject() == null ||  message.getSubject().isEmpty()) {
			throw new IllegalArgumentException(String.format("subject is not valid or null [%s]", message.getSubject()));
		} else if (message.getMessage() == null || message.getMessage().isEmpty()) {
			throw new IllegalArgumentException(String.format("message is not valid or null [%s]", message.getMessage()));
		} else if ((message instanceof HtmlMessage) && (((IHtmlMessage) message).getMessageHtml() == null || ((IHtmlMessage) message).getMessageHtml().isEmpty())) {
			throw new IllegalArgumentException(String.format("html message is not valid or null [%s]", ((IHtmlMessage) message).getMessageHtml()));
		}
		
		Email mail = null;
		
		if (message instanceof HtmlMessage) {
			mail = new HtmlEmail();
			
			IHtmlMessage hm = (IHtmlMessage) message;
			HtmlEmail he = (HtmlEmail) mail;
			he.setHtmlMsg(hm.getMessageHtml());
			he.setTextMsg(message.getMessage());
		} else if (message instanceof Message) {
			if (message.getAttachment() != null) {
				mail = new MultiPartEmail();
			} else {
				mail = new SimpleEmail();
			}
			
			mail.setMsg(message.getMessage());
		}
		
		if (message.getAttachment() != null) {
			EmailAttachment att = new EmailAttachment();
			att.setPath(message.getAttachment().getFileName());
			att.setDescription(message.getAttachment().getDescription());
			att.setName(message.getAttachment().getName());
			att.setDisposition(EmailAttachment.ATTACHMENT);
			
			MultiPartEmail mpe = (MultiPartEmail) mail;
			mpe.attach(att);
		}

		mail.setFrom(message.getFrom(), message.getName());
		mail.addTo(message.getTo());
		mail.setSubject(message.getSubject());
		
		mail.setHostName(message.getHost().getFqdn());
		mail.setSmtpPort(message.getHost().getPort());
		mail.addTo(message.getTo());

		if (message.getHost().isSsl()) {
			mail.setSSLOnConnect(message.getHost().isSsl());
		}
		
		if (message.getHost().getUsername() != null && message.getHost().getPassword() != null) {
			mail.setAuthentication(message.getHost().getUsername(), message.getHost().getPassword());
		}
		
		return mail;
	}

}
