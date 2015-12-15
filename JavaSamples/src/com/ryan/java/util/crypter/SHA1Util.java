package com.ryan.java.util.crypter;

import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class SHA1Util {
	public final static int bufferSize = 1024;

	public static String SHA1EncodeHex(byte[] data) {
		return byteArrayToHexString(SHA1Encode(data));
	}

	public static String SHA1EncodeFileHex(String fileName) {
		return byteArrayToHexString(SHA1EncodeFile(fileName));
	}

	public static byte[] SHA1EncodeFile(String fileName) {
		FileInputStream fis = null;
		DigestInputStream dis = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			fis = new FileInputStream(fileName);
			dis = new DigestInputStream(fis, md);
			byte[] data = new byte[bufferSize];
			while (dis.read(data) != -1)
				;
			return md.digest();
		} catch (Exception e) {
//			APILogUtil.writeLog(e);
			return null;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception ex) {
				}
			}
			if (dis != null) {
				try {
					dis.close();
				} catch (Exception ex) {
				}
			}
		}
	}

	public static byte[] SHA1Encode(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			return md.digest(data);
		} catch (Exception e) {
//			APILogUtil.writeLog(e);
		}
		return null;
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	public static String byteArrayToHexString(byte data[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			sb.append(byteToHexString(data[i]));
		}
		return sb.toString();
	}

	public static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return (new StringBuffer(String.valueOf(hexDigits[d1]))).append(hexDigits[d2]).toString();
	}
}
