package br.com.singularideas.labs.finance.utils.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.finance.utils.RestServiceException;
import br.com.singularideas.labs.finance.utils.conf.UtilsConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {UtilsConfig.class} )
public class RestServiceTest {

	@Autowired
	private RestService service;
	
	@Test
	public void testGet() {
		String quote = service.get("http://finance.yahoo.com/d/quotes.csv?e=.csv&f=sl1d1t1&s=USDGBP=X");
		assertNotNull(quote);
		System.out.println(quote);
	}

	@Test(expected=RestServiceException.class)
	public void testGetNullUrl() {
		String quote = service.get(null);
		assertNotNull(quote);
	}
	
	@Test(expected=RestServiceException.class)
	public void testGetInvalidUrl() {
		String quote = service.get("htt://www.google.com");
		assertNotNull(quote);
	}
	
}
