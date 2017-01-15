package br.com.singularideas.labs.finance.utils.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.finance.utils.conf.UtilsConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {UtilsConfig.class} )
public class CsvParserServiceTest {

	private static final String DEFAULT_CSV_TEST = "\"USDGBP=X\",0.6786,\"1/1/2016\",\"12:31pm\"";
	private static final String DEFAULT_CSV_TEST_SEPARATOR = "\"USDGBP=X\";0.6786;\"1/1/2016\";\"12:31pm\"";
	private static final String DEFAULT_CSV_TEST_SEPARATOR_QUOTE = "'USDGBP=X';0.6786;'1/1/2016';'12:31pm'";

	@Autowired
	private FileService fileService;
	
	@Autowired
	private CsvParserService parserService;
	
	@Test
	public void testParseFile() {
		File check = new File("/tmp/test1.txt");
		
		if (check.exists()) {
			check.delete();
		}
		
		File file = fileService.saveStringAsFile(DEFAULT_CSV_TEST, "/tmp/test1.txt");
		assertNotNull(file);
		
		List<String []> lines = parserService.parse(file);
		assertNotNull(lines);
		assertNotNull(lines.get(0));
		
		String [] cols = lines.get(0);
		assertNotNull(cols[0]);
		assertTrue(cols[0].equals("USDGBP=X"));

		printLines(lines);		
	}

	@Test
	public void testParseString() {
		List<String []> lines = parserService.parse(DEFAULT_CSV_TEST);
		assertNotNull(lines);
		assertNotNull(lines.get(0));
		
		String [] cols = lines.get(0);
		assertNotNull(cols[0]);
		assertTrue(cols[0].equals("USDGBP=X"));

		printLines(lines);
	}

	@Test
	public void testParseStringChar() {
		List<String []> lines = parserService.parse(DEFAULT_CSV_TEST_SEPARATOR, ';');
		assertNotNull(lines);
		assertNotNull(lines.get(0));
		
		String [] cols = lines.get(0);
		assertNotNull(cols[0]);
		assertTrue(cols[0].equals("USDGBP=X"));

		printLines(lines);
	}

	@Test
	public void testParseStringCharChar() {
		List<String []> lines = parserService.parse(DEFAULT_CSV_TEST_SEPARATOR_QUOTE, ';', '\'');
		assertNotNull(lines);
		assertNotNull(lines.get(0));
		
		String [] cols = lines.get(0);
		assertNotNull(cols[0]);
		assertTrue(cols[0].equals("USDGBP=X"));

		printLines(lines);
	}

	private void printLines(List<String[]> lines) {
		for (String [] cols : lines) {
			for (int i = 0; i < cols.length; i++) {
				System.out.println(String.format("col [%d]: %s", i, cols[i]));
			}
			
			System.out.println("--------------------------------------------------------------");
		}
	}

}
