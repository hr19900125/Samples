package com.ryan.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class MD5 {

	public static final Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();
	
	public static String encodePassword(String passwd,String salt){
		return md5Encoder.encodePassword(passwd, salt);
	}
}
