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
public class MailServiceHtmlTest {

	@Autowired
	private IHost host;
	
	@Autowired
	private MailService service;

	private IMessage message;
	
	@Before
	public void setUp() {
		message = new HtmlMessage(host);
		message.setFrom("luismr@ligflat.com.br");
		message.setName("Luis Machado Reis");
		message.setTo("luis.reis@singularideas.com.br");
	}
	
	@Test
	public void testSend() {
		message.setSubject("Send HTML mail");
		message.setMessage("Simple message in HTML");
		
		IHtmlMessage hm = (IHtmlMessage) message;
		hm.setMessageHtml("<html><body><h1>Simple message in HTML</h1></body></html>");
		
		service.send(message);
	}


	@Test
	public void testSendAttachment() {
		message.setSubject("Send HTML mail with Attachment");
		message.setMessage("Simple message in HTML with Attachment");

		IHtmlMessage hm = (IHtmlMessage) message;
		hm.setMessageHtml("<html><body><h1>Simple message in HTML</h1></body></html>");
		
		IAttachment att = new Attachment();
		att.setName("hosts");
		att.setDescription("hosts names");
		att.setFileName("/etc/hosts");
		
		message.setAttachment(att);
		
		service.send(message);
	}

	@Test(expected=MailException.class)
	public void testSendInvalidTo() {
		message.setSubject("Send HTML mail with Attachment");
		message.setMessage("Simple message in HTML with Attachment");
		message.setTo("invalid");

		IHtmlMessage hm = (IHtmlMessage) message;
		hm.setMessageHtml("<html><body><h1>Simple message in Text</h1></body></html>");
		
		service.send(message);
	}

}
