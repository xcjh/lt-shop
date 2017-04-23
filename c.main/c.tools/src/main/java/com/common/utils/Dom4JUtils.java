package com.common.utils;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4JUtils {
	
	/**
	 * 将xml映射到Map（暂支持一级）
	 * @param xmls
	 * @return
	 */
	public static Map parseXml(String xmls){
		SAXReader saxReader = new SAXReader();
		try {
			Map map = new HashMap<String, String>();
			Document document = saxReader.read(new ByteArrayInputStream(xmls.getBytes("UTF-8")));
			Element root = document.getRootElement();
			List<Element> list = root.elements();
			for (Element element : list) {
				map.put(element.getName(),element.getStringValue());
			}
			return map;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String xmls = "<xml>";
		xmls += "<return_code><![CDATA[SUCCESS]]></return_code>";
		xmls += "<return_msg><![CDATA[OK]]></return_msg>";
		xmls += "<appid><![CDATA[wx2421b1c4370ec43b]]></appid>";
		xmls += "<mch_id><![CDATA[10000100]]></mch_id>";
		xmls += "<nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>";
		xmls += "<sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>";
		xmls += "<result_code><![CDATA[SUCCESS]]></result_code>";
		xmls += "<prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>";
		xmls += "<trade_type><![CDATA[JSAPI]]></trade_type>";
		xmls += "</xml>";
		
		xmls = "<xml>";
		xmls += "<return_code><![CDATA[SUCCESS]]></return_code>";
		xmls += "<return_msg><![CDATA[OK]]></return_msg>";
		xmls += "<appid><![CDATA[wx6a03f64f207e5086]]></appid>";
		xmls += "<mch_id><![CDATA[1375969402]]></mch_id>";
		xmls += "<nonce_str><![CDATA[uarwmuzFKI74PkYU]]></nonce_str>";
		xmls += "<sign><![CDATA[E56F275ACCC2AE7B0C921A83EEA8E15C]]></sign>";
		xmls += "<result_code><![CDATA[FAIL]]></result_code>";
		xmls += "<err_code><![CDATA[OUT_TRADE_NO_USED]]></err_code>";
		xmls += "<err_code_des><![CDATA[商户订单号重复]]></err_code_des>";
		xmls += "</xml>";
		Map map = Dom4JUtils.parseXml(xmls);
		System.out.println(map.toString());
	}

}
