package com.ryan.sample.security;

import java.security.MessageDigest;

public class MD5Util {

	// private static final char HEX_DIG[] = { '0', '1', '2', '3', '4', '5',
	// '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
	// 'f' };

	public static String getMD5code(String string) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(string.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			return buf.toString();

		} catch (Exception e) {
			return "";
		}
	}
	
	public static void main(String[] args) {
		String str = "abcdefghijk1234567890";
		System.out.println("getMD5code = " + getMD5code(str));
	}

}
