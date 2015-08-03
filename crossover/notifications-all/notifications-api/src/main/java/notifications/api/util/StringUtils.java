/**
 * 
 */
package notifications.api.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author luismr
 *
 */
public abstract class StringUtils {

	public static String md5(String unencoded) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(unencoded.getBytes());

		byte byteData[] = md.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	public static boolean validateEmailAddress(String email) {
		EmailValidator validator = new EmailValidator();
		return validator.validate(email);
	}

}
