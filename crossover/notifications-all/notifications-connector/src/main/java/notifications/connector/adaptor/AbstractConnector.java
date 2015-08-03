/**
 * 
 */
package notifications.connector.adaptor;

import notifications.api.NotificationTO;
import notifications.api.SubscriberTO;
import notifications.connector.NotificationConnectorException;

/**
 * @author luismr
 *
 */
public abstract class AbstractConnector implements Sender {

	protected SubscriberTO subscriber;
	protected NotificationTO notification;
	
	/**
	 * Default Constructor 
	 */
	public AbstractConnector() {}
		
	/**
	 * Validate Destination Connector Settings
	 * 
	 * @throws NotificationConnectorException when is invalid
	 */
	protected abstract void validate() throws NotificationConnectorException;
	
	/**
	 * Process Job Connector
	 * 
	 * @throws NotificationConnectorException when has an error
	 */
	protected abstract void process() throws NotificationConnectorException;

	/**
	 * Send Notification to Subscriber
	 * @throws NotificationConnectorException when has an error
	 */
	public void send() throws NotificationConnectorException {
		validate();
		process();
	}

	/* Getters & Setters */
	public SubscriberTO getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(SubscriberTO subscriber) {
		this.subscriber = subscriber;
	}

	public NotificationTO getNotification() {
		return notification;
	}

	public void setNotification(NotificationTO notification) {
		this.notification = notification;
	}
	
	
}
