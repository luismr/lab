package br.com.singularideas.labs.knowhub.common.crypto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class CryptoUtils {

	public static String crypto(final String payload) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] bytesOfMessage = payload.getBytes("UTF-8");

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		
		return new String(thedigest);
	}
}
