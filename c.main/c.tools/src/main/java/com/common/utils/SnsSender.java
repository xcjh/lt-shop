package com.common.utils;

import java.util.HashMap;
import java.util.Map;

public class SnsSender {
	
	/**
	 * 批量发送
	 * @param mobile 手机号码，多个号码使用","分割
	 * @param msg 短信内容:您好，您的验证码是123456
	 */
	public static void batch(String mobile,String msg){
		String url = "http://222.73.117.156/msg/HttpBatchSendSM";// 应用地址
		String account = "qiyao888";// 账号
		String pswd = "Qiyao888";// 密码
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String extno = null;// 扩展码
		Map<String,String> params = new HashMap<String,String>();
		params.put("account", account);
		params.put("pswd", pswd);
		params.put("mobile", mobile);
		params.put("msg", msg);
		params.put("needstatus", "true");
		String result = HttpConnection.sendPost(url, params);
	}
	
	public static void main(String[] args) {
		SnsSender.batch("13811451717", "您好，您的验证码是123456");
	}
	
	

}
