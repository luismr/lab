package br.com.singularideas.labs.finance.quotes.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.singularideas.labs.finance.quotes.QueryQuoteException;
import br.com.singularideas.labs.finance.quotes.data.Currency;
import br.com.singularideas.labs.finance.quotes.data.Quote;
import br.com.singularideas.labs.finance.utils.service.CsvParserService;
import br.com.singularideas.labs.finance.utils.service.RestService;

@Service
public class QuoteService {

	private static final int FIRST = 0;

	private static String URL_PATTERN = "http://finance.yahoo.com/d/quotes.csv?e=.csv&f=sl1d1t1&s=%s%s=X";
	
	@Autowired
	private RestService restService;
	
	@Autowired
	private CsvParserService parserService;
	
	public Quote getQuote(final Currency from, final Currency to) {
		String r = restService.get(String.format(URL_PATTERN, to.name(), from.name()));
		
		Quote q = null;
		try {
			q = parse(r);
		} catch (Exception e) {
			throw new QueryQuoteException(e.getMessage(), e);
		}
		return q;
	}

	private Quote parse(String payload) throws ParseException {
		List<String []> lines = parserService.parse(payload);
		
		if (lines.size() > 1) {
			throw new QueryQuoteException("payload has more then 1 line!");
		}
		
		String [] cols = lines.get(FIRST);
		String to = cols[0].substring(0, 3);
		String from = cols[0].substring(3, 6);
		String dateInput = String.format("%s %s", cols[2], cols[3]);
		Date date = (new SimpleDateFormat("MM/dd/yyyy hh:mma")).parse(dateInput);
		Float value = Float.parseFloat(cols[1]);
		
		Quote q = new Quote();
		q.setTo(Currency.valueOf(to));
		q.setFrom(Currency.valueOf(from));
		q.setDate(date);
		q.setValue(value);
		
		return q;
	}
	
}
