package com.ryan.java.util.crypter;

import org.apache.commons.codec.binary.Base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {

    private static final String ALGORITHM_AES = "AES/ECB/PKCS5Padding";
    
    public static String encrypt(String content, String passwd){
        try{
            Cipher aesECB = Cipher.getInstance(ALGORITHM_AES);
            SecretKeySpec key = new SecretKeySpec(passwd.getBytes(), "AES");
            aesECB.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = aesECB.doFinal(content.getBytes());
            return Base64.encodeBase64String(result);
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
    
}
