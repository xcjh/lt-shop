package com.common.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
* @ClassName: AES 
* @Description: 加密工具，与.net通讯使用，密钥：301ADD2093748689；此处使用AES-128-ECB加密模式，key需要为16位
* http://www.oschina.net/code/snippet_242957_9931
* @author xiaoli
* @date 2016年12月14日 上午10:53:53 
*
 */
public class AES {
	
	// 加密
	public static String encrypt(String sSrc){
		try {
			return encrypt(sSrc,"301ADD2093748689");
		} catch (Exception e) {
			return "";
		}
	}
	
	// 加密
    public static String encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }
 
    // 解密
    public static String decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
 
    public static void main(String[] args) throws Exception {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
        String cKey = "301ADD2093748689";
        // 需要加密的字串
        String cSrc = "csfz1";//助手帐号
        cSrc = "yangjianhong";//勘查帐号
        System.out.println(cSrc);
        // 加密
        String enString = AES.encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + enString);
 
        // 解密
        String DeString = AES.decrypt(enString, cKey);
        System.out.println("解密后的字串是：" + DeString);
    }

}
