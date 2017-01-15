import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import org.junit.Test;

/**
 * 
 */

/**
 * @author luismr
 *
 */
public class ReadPhraseTest {

	@Test
	public void test() throws FileNotFoundException, FileAlreadyExistsException {
		ReadPhrase rpu = ReadPhrase.getInstance("src/test/resources/phrases.txt");
		
		String filename = "/tmp/out-phrases.txt";
		File file = new File(filename);
		
		if (file.exists()) {
			file.delete();
		}
		
		rpu.processMostFrequentPhrases(filename, 3);
		
		assertTrue(file.exists());
		
		printFile(file);
	}
	
	@Test
	public void testLordOfRingsExtraLarge() throws FileNotFoundException, FileAlreadyExistsException {
		ReadPhrase rpu = ReadPhrase.getInstance("src/test/resources/lordofrings-edited-largefile.txt");
		
		String filename = "/tmp/out-lordOfRings.txt";
		File file = new File(filename);

		if (file.exists()) {
			file.delete();
		}
		
		rpu.processMostFrequentPhrases(filename, 10);
		
		assertTrue(file.exists());
		
		printFile(file);		
	}
	
	private void printFile(final File file) {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
			for (String line; (line = br.readLine()) != null;) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
