package com.ryan.java.util.crypter;

public class ErypterMain {

    public static void main(String[] args){
        String inputString = "1234568";
        String outputString = AesUtil.encrypt(inputString, " !\"#$%&'()*+,-./");
        System.out.println("outputString = "+outputString);
        
        String reStr = AesUtil.decrypt(outputString, " !\"#$%&'()*+,-./");
        System.out.println("reStr = "+reStr);
    }
    
}
