/**
 * 
 */
package notifications.core;

/**
 * @author luismr
 *
 */
public class NotificationCoreException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotificationCoreException() {
		super();
	}

	public NotificationCoreException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotificationCoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotificationCoreException(String message) {
		super(message);
	}

	public NotificationCoreException(Throwable cause) {
		super(cause);
	}
	
}
