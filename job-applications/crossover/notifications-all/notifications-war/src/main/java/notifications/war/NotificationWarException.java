/**
 * 
 */
package notifications.war;

/**
 * @author luismr
 *
 */
public class NotificationWarException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public NotificationWarException() {
	}

	/**
	 * @param message
	 */
	public NotificationWarException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NotificationWarException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NotificationWarException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NotificationWarException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
