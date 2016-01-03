package br.com.singularideas.labs.finance.utils;

import org.junit.Test;

public class CSVParserServiceExceptionTest {

	@Test(expected=CSVParserServiceException.class)
	public void testCSVParserServiceException() {
		throw new CSVParserServiceException();
	}

	@Test(expected=CSVParserServiceException.class)
	public void testCSVParserServiceExceptionString() {
		throw new CSVParserServiceException("test");
	}

	@Test(expected=CSVParserServiceException.class)
	public void testCSVParserServiceExceptionThrowable() {
		RuntimeException rte = new RuntimeException("test");
		throw new CSVParserServiceException(rte);
	}

	@Test(expected=CSVParserServiceException.class)
	public void testCSVParserServiceExceptionStringThrowable() {
		RuntimeException rte = new RuntimeException("test");
		throw new CSVParserServiceException("test", rte);
	}

	@Test(expected=CSVParserServiceException.class)
	public void testCSVParserServiceExceptionStringThrowableBooleanBoolean() {
		RuntimeException rte = new RuntimeException("test");
		throw new CSVParserServiceException("test", rte, true, true);
	}

}
