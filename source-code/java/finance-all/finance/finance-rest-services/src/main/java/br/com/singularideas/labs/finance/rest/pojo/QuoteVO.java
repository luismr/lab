package br.com.singularideas.labs.finance.rest.pojo;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.singularideas.labs.finance.quotes.data.Quote;
import br.com.singularideas.labs.finance.rest.serializer.JsonDateSerializer;

public class QuoteVO extends Quote  {

	@Override
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDate() {
		return super.getDate();
	}
	
}
