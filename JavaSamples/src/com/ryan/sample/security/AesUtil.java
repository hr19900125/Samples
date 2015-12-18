package com.ryan.sample.security;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
/**
 * AES 加密/解密 工具类
 * 由于DES的不安全性以及DESede算法的低效,于是催生了AES算法(Advanced Encryption Standard)
 * AES 为对称加密算法
 * 该类使用长度16的密钥
 */
public class AesUtil {

    private static final String ALGORITHM_AES = "AES/ECB/PKCS5Padding";
    
    public static String encrypt(String content, String passwd){
        try{
            Cipher aesECB = Cipher.getInstance(ALGORITHM_AES);
            SecretKeySpec key = new SecretKeySpec(passwd.getBytes(), "AES");
            aesECB.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = aesECB.doFinal(content.getBytes());
            return new String(Base64.encodeBase64(result),"UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String decrypt(String content, String passwd){
        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM_AES);
            SecretKeySpec key = new SecretKeySpec(passwd.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = Base64.decodeBase64(content);
            return new String(cipher.doFinal(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
		String str = "abcdefghijk";
		String password = "!\"#$%&'()*+,-./0";
		String encryptStr = encrypt(str, password);
		System.out.println("encryptStr = " + encryptStr);
		
	}
    
}
