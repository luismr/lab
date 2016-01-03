package br.com.singularideas.labs.finance.quotes.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class CurrencyTest {

	@Test
	public void test() {
		System.out.println(Currency.BRL);
		System.out.println(Currency.BRL.toString());
		System.out.println(Currency.BRL.name());
		System.out.println(Currency.BRL.getName());
		System.out.println(Currency.BRL.getCountry());
		
		assertNotNull(Currency.BRL);
		assertTrue("BRL".equals(Currency.BRL.name()));
	}

}
