/**
 * 
 */
package notifications.connector;

/**
 * @author luismr
 *
 */
public class NotificationConnectorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotificationConnectorException() {
		super();
	}

	public NotificationConnectorException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotificationConnectorException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotificationConnectorException(String message) {
		super(message);
	}

	public NotificationConnectorException(Throwable cause) {
		super(cause);
	}
	
}
