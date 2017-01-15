package br.com.singularideas.labs.finance.quotes.data;

public enum Currency {

	BRL("Brazilian Real", "Brazil"),
	EUR("Euro", "Europe Community"),
	USD("Dollar US", "United States"),
	GBP("Britain Pound", "United Kingdom");
	
	private String name;
	private String country;
	
	private Currency(final String name, final String country) {
		this.name = name;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}
	
}
