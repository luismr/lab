package br.com.singularideas.labs.finance.utils;

import org.junit.Test;

public class RestServiceExceptionTest {

	@Test(expected=RestServiceException.class)
	public void testRestServiceException() {
		throw new RestServiceException();
	}

	@Test(expected=RestServiceException.class)
	public void testRestServiceExceptionThrowable() {
		RuntimeException rte = new RuntimeException("test");
		throw new RestServiceException(rte);
	}

	@Test(expected=RestServiceException.class)
	public void testRestServiceExceptionStringThrowableBooleanBoolean() {
		RuntimeException rte = new RuntimeException("test");
		throw new RestServiceException("test", rte, true, true);
	}

}
