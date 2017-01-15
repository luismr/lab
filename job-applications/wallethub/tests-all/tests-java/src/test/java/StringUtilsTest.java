import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

/**
 * Test StringUtils class
 */

/**
 * @author luismr
 *
 */
public class StringUtilsTest {

	@Test(expected=IllegalArgumentException.class)
	public void testIsPalindromInvalidStringWord() {
		String word = null;
		
		@SuppressWarnings("unused")
		boolean match = StringUtils.isPalindrome(word);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testIsPalindromInvalidCharWord() {
		char [] word = null;
		
		@SuppressWarnings("unused")
		boolean match = StringUtils.isPalindrome(word);
	}

	@Test
	public void testIsPalindromCharWord() {
		char [] word = "ALULA".toCharArray();
		boolean match = StringUtils.isPalindrome(word);
		assertTrue(match);
	}

	@Test
	public void testIsPalindromStringWord() {
		String word = "ALULA";
		boolean match = StringUtils.isPalindrome(word);
		assertTrue(match);
	}
	
	@Test
	public void testIsPalindromStringWordCaseSensitiveTrue() {
		String word = "Alula";
		boolean match = StringUtils.isPalindrome(word, true);
		assertFalse(match);
	}
	
	@Test
	public void testIsPalindromStringWordCaseSensitiveFalse() {
		String word = "Alula";
		boolean match = StringUtils.isPalindrome(word, false);
		assertTrue(match);
	}
	
	@Test
	public void testCountPalindromes() {
		BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/words.txt")));
		
		int words = 0; 
		int palindromes = 0;
		
		try {
		    for(String line; (line = br.readLine()) != null; ) {
		    	words++;
		    	
		    	if (StringUtils.isPalindrome(line)) {
		    		// UNCOMENT line above to see palindromes found on words.txt
		    		// System.out.println(line);
		    		palindromes++;
		    	}
		    }
		    
		    System.out.println("\n\n");
		    System.out.println("Words .......: " + words);
		    System.out.println("Palindromes .: " + palindromes);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		assertTrue(palindromes == 91);
	}

	@Test
	public void testGetMd5Hash() {
		String input = "very cool hash";
		String hash = StringUtils.getMd5Hash(input);
		assertTrue("1cb13cf95eb87ae12ce35cd7ba1eae62".equals(hash));		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetMd5HashInvalidInput() {
		String input = null;
		String hash = StringUtils.getMd5Hash(input);
		assertTrue(hash != null);
	}
}
