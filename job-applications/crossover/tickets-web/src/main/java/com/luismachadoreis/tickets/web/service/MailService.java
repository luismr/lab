package com.luismachadoreis.tickets.web.service;

import com.luismachadoreis.tickets.web.MailException;
import com.luismachadoreis.tickets.web.mail.IMessage;
import com.luismachadoreis.tickets.web.mail.MailFactory;
import org.apache.commons.mail.Email;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class MailService {

	public void send(final IMessage message) {
		try {
			Email mail = MailFactory.create(message);
			mail.send();
		} catch (Exception e) {
			throw new MailException(e.getMessage(), e);
		}
		
	}

	@Async
	public Future<Email> enqueue(final IMessage message) {
		Email mail = null;

		try {
			mail = MailFactory.create(message);
			mail.send();
		} catch (Exception e) {
			throw new MailException(e.getMessage(), e);
		}

		return new AsyncResult<Email>(mail);
	}
}
