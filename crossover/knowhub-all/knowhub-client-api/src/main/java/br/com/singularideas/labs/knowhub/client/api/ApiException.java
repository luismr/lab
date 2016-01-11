package br.com.singularideas.labs.knowhub.client.api;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiException() {
		super();
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiException(String message) {
		super(message);
	}

	public ApiException(Throwable cause) {
		super(cause);
	}

}
