package com.ryan.java.util.common;

import java.security.SecureRandom;

public class RandomUtil {
	public static int getInt(int min, int max) {
		return min + new Double(Math.random() * (max - min)).intValue();
	}

	public static String getRandomString(int length) {
		SecureRandom ran = new SecureRandom();
		String rt = "";
		String all = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
		int rint = 0;
		for (int i = 0; i < length; i++) {
			rint = ran.nextInt();
			if (rint < 0) {
				rint = -rint;
			}
			rint %= all.length();
			rt = rt + all.substring(rint, rint + 1);
		}
		return rt;
	}
}