package br.com.singularideas.labs.finance.utils;

public class RestServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RestServiceException() {
	}

	public RestServiceException(String message) {
		super(message);
	}

	public RestServiceException(Throwable cause) {
		super(cause);
	}

	public RestServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
