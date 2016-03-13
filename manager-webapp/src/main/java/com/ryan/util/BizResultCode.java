package com.ryan.util;
/**
 * 业务相关的code 
 */
public class BizResultCode {

	private static final int BASE = 10000;
	
	/**
	 * 获取用户失败error
	 */
	public static final int USER_GET_ERROR = BASE + 1;
	
	/**
	 * 修改密码，密码验证不通过，老密码错误
	 */
	public static final int OLD_PASSWORD_WROING = BASE + 2;
	
	/**
	 * 修改密码错误
	 */
	public static final int CHANGE_PASSWORD_ERROR = BASE + 3;
	
}
