package br.com.singularideas.labs.finance.quotes;

public class QueryQuoteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public QueryQuoteException() {
	}

	public QueryQuoteException(String message) {
		super(message);
	}

	public QueryQuoteException(Throwable cause) {
		super(cause);
	}

	public QueryQuoteException(String message, Throwable cause) {
		super(message, cause);
	}

	public QueryQuoteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
