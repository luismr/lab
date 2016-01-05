package br.com.singularideas.labs.finance.quotes.data;

import java.util.Date;

public class Quote {

	private Currency from;
	private Currency to;
	private Float value;
	private Date date;
	
	public Quote() {}

	public Currency getFrom() {
		return from;
	}

	public void setFrom(Currency from) {
		this.from = from;
	}

	public Currency getTo() {
		return to;
	}

	public void setTo(Currency to) {
		this.to = to;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Quote [from=" + from + ", to=" + to + ", value=" + value + ", date=" + date + "]";
	}
}
