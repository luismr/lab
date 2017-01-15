/**
 * 
 */
package notifications.connector.adaptor;

import javax.mail.internet.MimeMessage.RecipientType;

import notifications.api.util.StringUtils;
import notifications.connector.NotificationConnectorException;
import notifications.connector.SendEmailException;

import org.codemonkey.simplejavamail.Email;
import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Connector for Email SMTP sender
 * 
 * @author luismr
 *
 */
@Component
public class EmailConnector extends AbstractConnector {

	private String host;
	private Integer port;
	private String username;
	private String password;
	private String fromAddress;
	private String fromName;
	
	@Autowired
	public EmailConnector(Environment env) {
		this.host = env.getProperty("server.host");
		this.port = Integer.parseInt(env.getProperty("server.port"));
		this.username = env.getProperty("server.username");
		this.password = env.getProperty("server.password");
		this.fromName = env.getProperty("email.from.name");
		this.fromAddress = env.getProperty("email.from.address");
		
	}

	/*
	 * (non-Javadoc)
	 * @see notifications.connector.adaptor.AbstractConnector#validate()
	 */
	@Override
	protected void validate() throws NotificationConnectorException {
		if (subscriber.getEmail() == null
				|| StringUtils.validateEmailAddress(subscriber.getEmail()) == false) {
			throw new SendEmailException("Invalid E-mail Address");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see notifications.connector.adaptor.AbstractConnector#process()
	 */
	@Override
	protected void process() throws NotificationConnectorException {
		Email email = emailFromNotification();
		Mailer sender = new Mailer(host, port, username, password, TransportStrategy.SMTP_SSL);
		sender.sendMail(email);
	}

	/**
	 * Generate an Email from Notification
	 * 
	 * @param subscriber
	 * @param notification
	 * @return
	 */
	private Email emailFromNotification() {
		Email email = new Email();
		email.setFromAddress(fromName, fromAddress);
		email.setSubject(notification.getTitle());
		email.setText(notification.getData());
		email.addRecipient(subscriber.getName(), subscriber.getEmail(), RecipientType.TO);

		return email;
	}

}
