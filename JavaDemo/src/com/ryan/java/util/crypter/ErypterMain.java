package com.ryan.java.util.crypter;

public class ErypterMain {

    public static void main(String[] args){
        String inputString = "1234568";
        String outputString = AesUtil.encrypt(inputString, "!\"#$%&'()*+,-./0");
        System.out.println("outputString = "+outputString);
        
        String reStr = AesUtil.decrypt(outputString, "!\"#$%&'()*+,-./0");
        System.out.println("reStr = "+reStr);
    }
    
}
