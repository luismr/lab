package com.luismachadoreis.tickets.web.mail;

import com.luismachadoreis.tickets.web.MailException;
import com.luismachadoreis.tickets.web.UnitTestContext;
import com.luismachadoreis.tickets.web.config.MailConfig;
import com.luismachadoreis.tickets.web.service.MailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={UnitTestContext.class, MailConfig.class})
public class MailServiceTextTest {

	@Autowired
	private IHost host;
	
	@Autowired
	private MailService service;

	private IMessage message;
	
	@Before
	public void setUp() {
		message = new Message(host);
		message.setFrom("luismr@ligflat.com.br");
		message.setName("Luis Machado Reis");
		message.setTo("luis.reis@singularideas.com.br");
	}
	
	@Test
	public void testSend() {
		message.setSubject("Send TXT mail");
		message.setMessage("Simple message in Text");
		service.send(message);
	}


	@Test
	public void testSendAttachment() {
		message.setSubject("Send TXT mail with Attachment");
		message.setMessage("Simple message in Text with Attachment");

		IAttachment att = new Attachment();
		att.setName("hosts");
		att.setDescription("hosts names");
		att.setFileName("/etc/hosts");
		
		message.setAttachment(att);
		
		service.send(message);
	}

	@Test(expected=MailException.class)
	public void testSendInvalidTo() {
		message.setSubject("Send TXT mail with Attachment");
		message.setMessage("Simple message in Text with Attachment");
		message.setTo("invalid");
		service.send(message);
	}

}
