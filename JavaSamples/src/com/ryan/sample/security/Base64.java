package com.ryan.sample.security;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 使用 BASE64Decoder/BASE64Encoder 进行base64的加密/解密操作
 */
public class Base64 {

	/**
	 * Base64 解密
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static byte[] decryptBASE64(String key) throws IOException {
		return (new BASE64Decoder()).decodeBuffer(key);
	}
	
	/**
	 * Base64 加密
	 * @param key
	 * @return
	 */
	public static String encryptBASE64(byte[] key) {
		return (new BASE64Encoder()).encodeBuffer(key);
	}
	
}
