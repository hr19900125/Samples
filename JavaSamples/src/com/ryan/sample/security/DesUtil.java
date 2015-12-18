package com.ryan.sample.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * DES 加密/解密 工具类
 * DES为对称加密算法，现在用的越来越少，原因是因为DES使用56位密钥，以现代计算能力，24小时内即可被破解,对称加密算法可以使用AES
 * DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DesUtil {

	public static byte[] encrypt(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}

	public static void main(String[] args) throws Exception {
		String str = "woshimingwen";
		String password = "12345678";
		byte[] bytes = encrypt(str.getBytes(), password);
		// 使用base64加密
		String encryptStr = Base64.encode(bytes);
		System.out.println("encryptStr = " + encryptStr);
		bytes = Base64.decode(encryptStr);
		String decryptStr = new String(decrypt(bytes, password));
		System.out.println("decryptStr = " + decryptStr);
	}

}
