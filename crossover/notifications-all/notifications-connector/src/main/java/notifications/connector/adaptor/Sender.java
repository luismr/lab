/**
 * 
 */
package notifications.connector.adaptor;

import notifications.connector.NotificationConnectorException;

/**
 * @author luismr
 *
 */
public interface Sender {

	public void send() throws NotificationConnectorException;
	
}
