package br.com.singularideas.labs.finance.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.singularideas.labs.finance.quotes.data.Currency;
import br.com.singularideas.labs.finance.quotes.data.Quote;
import br.com.singularideas.labs.finance.quotes.service.QuoteService;
import br.com.singularideas.labs.finance.rest.pojo.QuoteVO;

@Controller
@RequestMapping("/currency")
public class CurrencyController {

	@Autowired
	private QuoteService quotes;
	
	@RequestMapping(value = "/quote/{from}/{to}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Quote quote(final @PathVariable String from, final @PathVariable String to) {
		Quote quote = quotes.getQuote(Currency.valueOf(from), Currency.valueOf(to));
		
		QuoteVO vo = new QuoteVO();
		vo.setDate(quote.getDate());
		vo.setFrom(quote.getFrom());
		vo.setTo(quote.getTo());
		vo.setFrom(quote.getFrom());
		vo.setValue(quote.getValue());
		
		return vo;	
	}
	
}
