package br.com.singularideas.labs.finance.utils.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

import br.com.singularideas.labs.finance.utils.CSVParserServiceException;

@Service
public class CsvParserService {

	private static final char DEFAULT_QUOTECHAR = '"';
	private static final char DEFAULT_SEPARATOR = ',';

	public List<String[]> parse(final File file) {
		try(CSVReader reader = new CSVReader(new FileReader(file))) {
			return reader.readAll();
		} catch (Exception e) {
			throw new CSVParserServiceException(e.getMessage(), e);
		}
	}

	public List<String[]> parse(final String payload) {
		return parse(payload, DEFAULT_SEPARATOR ,DEFAULT_QUOTECHAR);
	}
	
	public List<String[]> parse(final String payload, final char separator) {
		return parse(payload, separator, DEFAULT_QUOTECHAR);
	}
	
	public List<String[]> parse(final String payload, final char separator, final char quotechar) {
		CSVParser parser = new CSVParser(separator, quotechar);
		List<String[]> rows = new ArrayList<String[]>();
		
		String [] lines = payload.split("\\n");
		for (int i = 0; i < lines.length; i++) {
			try {
				String [] cols = parser.parseLine(lines[i]);
				rows.add(cols);
			} catch (IOException e) {
				throw new CSVParserServiceException(e.getMessage(), e);
			}
		}
		
		return rows;
	}
	
}
