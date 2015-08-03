/**
 * 
 */
package notifications.connector;


/**
 * @author luismr
 *
 */
public class SendEmailException extends NotificationConnectorException {

	private static final long serialVersionUID = 1L;

	public SendEmailException() {
		super();
	}

	public SendEmailException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SendEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public SendEmailException(String message) {
		super(message);
	}

	public SendEmailException(Throwable cause) {
		super(cause);
	}

	
	
}
