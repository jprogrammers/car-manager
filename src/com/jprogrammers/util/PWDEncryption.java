package com.jprogrammers.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Vahid Forghani
 */

public class PWDEncryption {

    //add this appender to password to make it very secure
	private static final String appender= "$@@#jprogrammers^$%";
	
	public static String encrypt(String password) {
		
		password += appender;
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            // convert the byte to hex format method 1
            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16)
                        .substring(1));
            }

            return sb.toString();
		} catch (NoSuchAlgorithmException e) {
            return null;
		}
	}
	
	public static boolean isEqual(String password, String encryptedPass) {
		password = encrypt(password);
		return password.equals(encryptedPass);
	}
}
