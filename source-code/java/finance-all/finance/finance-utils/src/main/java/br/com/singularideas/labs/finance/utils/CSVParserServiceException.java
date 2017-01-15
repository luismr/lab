package br.com.singularideas.labs.finance.utils;

public class CSVParserServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CSVParserServiceException() {
	}

	public CSVParserServiceException(String message) {
		super(message);
	}

	public CSVParserServiceException(Throwable cause) {
		super(cause);
	}

	public CSVParserServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public CSVParserServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
