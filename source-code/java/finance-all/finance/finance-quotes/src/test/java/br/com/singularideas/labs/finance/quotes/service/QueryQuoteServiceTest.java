package br.com.singularideas.labs.finance.quotes.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.finance.quotes.conf.QuotesConfig;
import br.com.singularideas.labs.finance.quotes.data.Currency;
import br.com.singularideas.labs.finance.quotes.data.Quote;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {QuotesConfig.class} )
public class QueryQuoteServiceTest {

	@Autowired
	private QuoteService service;
	
	@Test
	public void testGetQuote() {
		Quote q = service.getQuote(Currency.BRL, Currency.USD);
		assertNotNull(q);
		assertTrue("BRL".equals(q.getFrom().name()));
		
		System.out.println(q);
	}

}
