package br.com.singularideas.labs.finance.batches;

import java.io.File;
import java.text.SimpleDateFormat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.singularideas.labs.finance.quotes.data.Currency;
import br.com.singularideas.labs.finance.quotes.data.Quote;
import br.com.singularideas.labs.finance.quotes.service.QuoteService;
import br.com.singularideas.labs.finance.utils.service.FileService;
import br.com.singularideas.labs.finance.utils.service.JsonParserService;

public class QuotesBatch {

	private static final int STATUS_INVALID_ARGUMENTS = -1;
	private static final int STATUS_INVALID_CURRENCIES = -2;

	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("Error: you must specify FROM and TO currencies");
			printUsage();
			System.exit(STATUS_INVALID_ARGUMENTS);
		}
		
		Currency to = null;
		Currency from = null;
		
		try {
			to = Currency.valueOf(args[1].toUpperCase());
			from = Currency.valueOf(args[0].toUpperCase());
		} catch (Exception e) {
			System.err.println("Invalid Currency code!");
			printUsage();
			System.exit(STATUS_INVALID_CURRENCIES);
		}
		
		String path = "";
		if (args.length == 3 && args[2] != null) {
			path = args[2] + File.separator;
		}
		
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(BatchesConfig.class);
		QuoteService quoteService = ctx.getBean(QuoteService.class);
		FileService fileService = ctx.getBean(FileService.class);
		JsonParserService jsonService = ctx.getBean(JsonParserService.class);
		
		Quote quote = quoteService.getQuote(from, to);
		String timestamp = (new SimpleDateFormat("yyyyMMddHHmmss")).format(quote.getDate());
		
		StringBuffer payload = new StringBuffer();
		payload.append(jsonService.encode(quote));
		payload.append("\n");
		
		String filename = String.format("%s%s-%s-%s.json", path, quote.getFrom().name(), quote.getTo().name(), timestamp);

		fileService.saveStringAsFile(payload.toString(), filename);
		
		System.out.println("");
		System.out.println(payload.toString());
	}
	
	private static void printUsage() {
		System.err.println("");
		System.err.println("Usage: quotes.sh <FROM> <TO> [pathTo]");
		System.err.println("       <FROM> and <TO> currency code");
		System.err.println("          USD - American Dollar");
		System.err.println("          EUR - Euro");
		System.err.println("          GBP - Britain Pound");
		System.err.println("          BRL - Brazilian Real");
		System.err.println("       [pathTo] - where you want to write your file");
		System.err.println("");
	}
}
