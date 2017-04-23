package com.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @ClassName: AESUtils
 * @Description: AES 加密解密工具类，算法模式:CBC;密码长度：128；补码方式 PKCS5Padding；加密结果编码方式 BASE64；密钥101ADD2093748689；向量201BBC0108794710
 * http://www.seacha.com/tools/aes.html
 * @author xiaoli
 * @date 2016年12月9日 上午10:42:53
 *
 */
public class AESUtils {

	/*
	 * 密钥 可以用26个字母和数字组成 ，key需要为16位。
	 */
	private String sKey = "101ADD2093748689";
	/**
	 * 密钥偏移向量，16位
	 */
	private String ivParameter = "201BBC0108794710";
	private static AESUtils instance = null;

	private AESUtils() {  
  
    }

	public static AESUtils getInstance() {
		if (instance == null)
			instance = new AESUtils();
		return instance;
	}

	/**
	 * 
	* @Title: encrypt 
	* @Description: 加密
	* @return String 
	* @throws
	 */
	public String encrypt(String sSrc) {
		String result = "";
		try {
			Cipher cipher;
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
			result = new BASE64Encoder().encode(encrypted);
//			result = URLEncoder.encode(result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 此处使用BASE64做转码。
		return result;

	}

	/**
	 * 
	* @Title: decrypt 
	* @Description: 解密
	* @return String 
	* @throws
	 */
	public String decrypt(String sSrc) {
		try {
//			sSrc = URLDecoder.decode(sSrc, "UTF-8");
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		// 需要加密的字串
		String cSrc = "INNER#lubaocar";
		System.out.println(cSrc + "  长度为" + cSrc.length());
		// 加密
		long lStart = System.currentTimeMillis();
		String enString = AESUtils.getInstance().encrypt(cSrc);
		System.out.println("加密后的字串是：" + enString + "长度为" + enString.length());

		long lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("加密耗时：" + lUseTime + "毫秒");
		// 解密
		lStart = System.currentTimeMillis();
		String DeString = AESUtils.getInstance().decrypt(enString);
		System.out.println("解密后的字串是：" + DeString);
		lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("解密耗时：" + lUseTime + "毫秒");
		
	}
}
