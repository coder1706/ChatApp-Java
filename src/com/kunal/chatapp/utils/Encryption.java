package com.kunal.chatapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encryption {
	
	public static String encryptPassword(String plainPassword) throws NoSuchAlgorithmException {
		String encryptedPass = null;
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(plainPassword.getBytes());
		
		byte[] encrypt = messageDigest.digest();
		StringBuffer sb = new StringBuffer();
		for(byte b: encrypt) {
			sb.append(b);
		}
		encryptedPass = sb.toString();
		
		return encryptedPass;
	}
}
