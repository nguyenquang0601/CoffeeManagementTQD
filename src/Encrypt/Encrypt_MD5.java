/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encrypt;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author nguyenquang
 */
public class Encrypt_MD5 {
   
    public static String encrypt(String srcText) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String enrText = null;
        
        MessageDigest msd = MessageDigest.getInstance("MD5");
        byte[] srcTextBytes = srcText.getBytes("UTF-8");
        byte[] enrTextBytes = msd.digest(srcTextBytes);
        BigInteger bigInteger = new BigInteger(1, enrTextBytes);
        enrText = bigInteger.toString(16);
        return enrText;
    } 
//    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        String srcText = "123456";
//        String srcText1 = "123456789";
//        String enrText = MD5Encryptor.encrypt(srcText);
//        String enrText1 = MD5Encryptor.encrypt(srcText1);
//        
//        System.out.println("Encrypt: " + enrText);
//        System.out.println("Encrypt1: " +enrText1);
//    }
}
