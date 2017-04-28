package com.common;

/**
 * 
* @ClassName: Constant 
* @Description: 常量定义类
* @author xiaoli
* @date 2016年10月8日 下午4:34:50 
*
 */
public class Constant {
	
	public static Integer CURRENT_SYSTEM_CATEGORY =0;//当前系统类别，1:平台,2:托运方,3:承运方
	public static final Long SYSTEM_CATEGORY_MANAGER = 1l;//平台
	public static final Long SYSTEM_CATEGORY_SHIPPER = 2l;//托运方
	public static final Long SYSTEM_CATEGORY_CARRISE = 3l;//承运方
	public static final String SESSION_USERNAME = "session_username";//保存用户名到session，给websocket使用
	public static final String WEBSOCKET_USERNAME = "websocket_username";
	public static String CONTEXTPATH = "";
	public static String SITE_LOGIN_USER = "site_login";//前台用户登录保存变量
	public static String ADMIN_LOGIN_USER = "admin_login";//后台用户登录保存变量
	// weixin
	public static String WEIXIN_APP_ID = "";
	public static String WEIXIN_MCHID = "";
	public static String WEIXIN_NOTIFY_URL = "";
	
	public static String WEIXIN_API_SECURITY_KEY = "";
	
	/**
	 * 
	* @Title: jointSocketName 
	* @Description: 拼接socket连接用户名称
	* @return String 
	* @throws
	 */
	public static String joinSocketName(Long uid){
		return "ws_"+uid;
	}
	
	

}
