package br.com.singularideas.labs.finance.utils;

import org.junit.Test;

public class FileServiceExceptionTest {

	@Test(expected=FileServiceException.class)
	public void testFileServiceException() {
		throw new FileServiceException();
	}

	@Test(expected=FileServiceException.class)
	public void testFileServiceExceptionString() {
		throw new FileServiceException("exception");
	}

	@Test(expected=FileServiceException.class)
	public void testFileServiceExceptionThrowable() {
		RuntimeException rte = new RuntimeException("error");
		throw new FileServiceException(rte);
	}

	@Test(expected=FileServiceException.class)
	public void testFileServiceExceptionStringThrowable() {
		RuntimeException rte = new RuntimeException("error");
		throw new FileServiceException("error", rte);
	}

	@Test(expected=FileServiceException.class)
	public void testFileServiceExceptionStringThrowableBooleanBoolean() {
		RuntimeException rte = new RuntimeException("error");
		throw new FileServiceException("error", rte, true, true);
	}

}
