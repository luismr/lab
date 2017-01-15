import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * String Utils Functions
 */

/**
 * @author luismr
 */
public abstract class StringUtils {

	/**
	 * Returns TRUE if given word is a palindrome
	 * @param word a {@link String} representing a word
	 * @return TRUE if it is a palindrome
	 */
	public static boolean isPalindrome(final String word) {
		return isPalindrome(word, false);
	}
	
	/**
	 * Returns TRUE if a given word is a palindrome
	 * @param word a {@link String} representing a word
	 * @param casesensitive indicate if case sensitive 
	 * 						is important to your answer
	 * @return TRUE if it is a palindrome
	 */
	public static boolean isPalindrome(final String word, final boolean casesensitive) {
		if (word == null) {
			throw new IllegalArgumentException("word must be not null");
		}
		
		String eval = (casesensitive) ? word : word.toUpperCase();
		
		return isPalindrome(eval.toCharArray());		
	}
	
	/**
	 * Returns TRUE if given word is a palindrom
	 * @param word a {@link char} array representing a word
	 * @return TRUE if it is a palindrom
	 */
	public static boolean isPalindrome(char[] word) {
		if (word == null) {
			throw new IllegalArgumentException("word must be not null");			
		}
		
		boolean match = true;
		
		int forward = 0;
		int backward = word.length - 1;
		
		while ((backward > forward) && match) {
			if (word[forward] != word[backward]) {
				match = false;
			}
			
			++forward;
			--backward;
		}
		
		return match;
	}
	
	/**
	 * Generate a MD5 Hash
	 * @param str input string
	 * @return generated MD5 hash
	 */
	public static String getMd5Hash(final String str) {
		if (str == null) {
			throw new IllegalArgumentException("str == null");
		}
		
		String hash = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			
			hash = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Error", e);
		}
		
		return hash;
	}

}
