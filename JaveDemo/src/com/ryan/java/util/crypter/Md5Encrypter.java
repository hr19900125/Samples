package com.ryan.java.util.crypter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encrypter {
	public static byte[] md5Bytes(String text) {
		if ((text == null) || ("".equals(text))) {
			return new byte[0];
		}

		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(
					"System doesn't support MD5 algorithm.");
		}

		msgDigest.update(text.getBytes());
		byte[] bytes = msgDigest.digest();
		return bytes;
	}

	public static String md5(String text, boolean isReturnRaw) {
		if ((text == null) || ("".equals(text))) {
			return text;
		}

		byte[] bytes = md5Bytes(text);
		if (isReturnRaw) {
			return new String(bytes);
		}

		String md5Str = new String();

		for (int i = 0; i < bytes.length; i++) {
			byte tb = bytes[i];

			char tmpChar = (char) (tb >>> 4 & 0xF);
			char high;
			if (tmpChar >= '\n') {
				high = (char) ('a' + tmpChar - 10);
			} else {
				high = (char) ('0' + tmpChar);
			}
			md5Str = md5Str + high;

			tmpChar = (char) (tb & 0xF);
			char low;
			if (tmpChar >= '\n') {
				low = (char) ('a' + tmpChar - 10);
			} else {
				low = (char) ('0' + tmpChar);
			}
			md5Str = md5Str + low;
		}

		return md5Str;
	}

	public static String md5(String text) {
		return md5(text, false);
	}

	public static String md5Yahoo(String text) {
		if ((text == null) || ("".equals(text))) {
			return text;
		}

		byte[] bytes = md5Bytes(text);
		return Base64.encodeYahoo64(bytes);
	}
}
