package com.ryan.util;

import java.util.regex.Pattern;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class TextUtils {

	/**
	 * 判断字符串是否为空
	 * str == null true
	 * str == "" true
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(String text){
		return text == null || "".equals(text);
	}
	/**
	 * 判断字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	} 
	
	public static void main(String[] args){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		System.out.println(md5.encodePassword("123456", WebConstant.PASSWD_MD5_KEY));
	}
}
