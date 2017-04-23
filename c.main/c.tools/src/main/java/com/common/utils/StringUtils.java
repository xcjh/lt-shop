package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	
	/**
	 * 验证一个字符串是否为空
	 * @author xiaokx
	 * @date 2013-7-17下午6:10:32
	 * @param str
	 * @return 如果为空返回真，如果不为空返回假
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str))
			return true;

		String pattern = "\\S";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(str);
		return !m.find();

	}
	
	/**
	 * 
	* @Title: checkUname 
	* @Description: 帐号验证；3-16位，字母、数字、下划线组合
	* @return boolean 
	* @throws
	 */
	public static boolean validUname(String uname){
		 boolean flag = false;  
	     try{  
		      String check = "^[a-zA-Z0-9_]{3,16}$";  
		      Pattern regex = Pattern.compile(check);  
		      Matcher matcher = regex.matcher(uname);  
		      flag = matcher.matches();  
	     }catch(Exception e){  
	    	 flag = false;  
	     }  
	       
	     return flag;  
	}
	
	/**
	 * 
	* @Title: checkUpwd 
	* @Description: 密码验证；6-20位，字母、数字、字符
	* @return boolean 
	* @throws
	 */
	public static boolean validUpwd(String upwd){
		 boolean flag = false;  
	     try{  
		      String check = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]){6,20}$";  
		      Pattern regex = Pattern.compile(check);  
		      Matcher matcher = regex.matcher(upwd);  
		      flag = matcher.matches();  
	     }catch(Exception e){  
	    	 flag = false;  
	     }  
	       
	     return flag;  
	}
	
	/**  
     * 验证邮箱地址是否正确  
     * @param email  
     * @return  
     */  
    public static boolean validEmail(String email){  
	     boolean flag = false;  
	     try{  
		      String check = "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		      Pattern regex = Pattern.compile(check);  
		      Matcher matcher = regex.matcher(email);  
		      flag = matcher.matches();  
	     }catch(Exception e){  
	    	 flag = false;  
	     }  
	       
	     return flag;  
    }  
    /**  
     * 验证手机号码  
     * @param mobiles  
     * @return  
     */  
    public static boolean validMobile(String mobiles){  
    	boolean flag = false;  
	     try{  
		      String check = "^1[34578]\\d{9}$";  
		      Pattern regex = Pattern.compile(check);  
		      Matcher matcher = regex.matcher(mobiles);  
		      flag = matcher.matches();  
	     }catch(Exception e){  
	    	 flag = false;  
	     }  
	       
	     return flag;  
    }  
	
	

	/**
	 * 验证字符最大长度
	 * 
	 * @param str
	 * @return
	 */
	public static boolean validMaxLen(String str, int length) {
		if (str == null && str.equals("")) {
			return false;
		}
		if (str.length() > length) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 验证字符最小长度
	 * 
	 * @param str
	 * @return
	 */
	public static boolean validMinLen(String str, int length) {
		if (str == null && str.equals("")) {
			return false;
		}
		if (str.length() < length) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 转换图片的名称
	 * @param filePath  如：http://static.eop.com/user/1/1/attachment/goods/2001010101030.jpg
	 * @param shortName 
	 * @return
	 */
	public static  String getThumbPath(String filePath, String shortName) {
		String pattern = "(.*)([\\.])(.*)";
		String thumbPath = "$1" + shortName + "$2$3";

		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(filePath);
		if (m.find()) {
			String s = m.replaceAll(thumbPath);

			return s;
		}
		return null;
	}
	
	
	/**
	 * 检测某字串是否存在某数组中
	 * 
	 * @param value
	 * @param array
	 * @return 存在返回真，不存在返回假
	 */
	public static boolean isInArray(String value, String[] array) {
		if (array == null)
			return false;
		for (String v : array) {
			if (v.equals(value))
				return true;
		}
		return false;

	}
	
	
	private static HashMap<String,Integer> snmap = new HashMap<String, Integer>();	
	public static synchronized String createSn() {
		Date now = new Date();
		String sn = DateUtils.toString(now,
		"yyyyMMddHHmmss");
//		if(snmap.containsKey(sn)){
//			int index=snmap.get(sn).intValue()+1;
//			snmap.put(sn, new Integer(index));
//			sn = sn + index;
//		}else{
//			snmap.clear();
//			snmap.put(sn, 0);
//		}
		return sn;
	}
	
	/**
	 * 去除HTML 元素
	 * 
	 * @param element
	 * @return
	 */
	public static String getTxtWithoutHTMLElement(String element) {

		if (null == element || "".equals(element.trim())) {
			return element;
		}

		Pattern pattern = Pattern.compile("<[^<|^>]*>");
		Matcher matcher = pattern.matcher(element);
		StringBuffer txt = new StringBuffer();
		while (matcher.find()) {
			String group = matcher.group();
			if (group.matches("<[\\s]*>")) {
				matcher.appendReplacement(txt, group);
			} else {
				matcher.appendReplacement(txt, "");
			}
		}
		matcher.appendTail(txt);
		String temp = txt.toString().replaceAll("\n", "");
		temp = temp.replaceAll(" ", "");
		return temp;
	}
	
	/**
	 * 获取文本中的图片地址
	 * @author xiaokx
	 * @date 2013-9-11上午11:18:02
	 * @param content
	 * @return
	 */
	public static List<String> getImgLinkByContent(String content){
		String link = "";
		String[] str=content.split("img");
		List<String> list=new ArrayList<String>();
		for(int i=1;i<str.length;i++){
			String[] strn=str[i].split("src")[1].replace("=\"", "").split("\"");
			String url = strn[0];
			if(StringUtils.isEmpty(url) || url.indexOf("/xheditor/xheditor_emot/default")>-1)continue;
			list.add(url);
		}
		if(list.size()==0)return null;
		return list;
	}
	
	/**
	 * 检查浮点数
	 * 
	 * @param num
	 * @param type
	 *            "0+":非负浮点数 "+":正浮点数 "-0":非正浮点数 "-":负浮点数 "":浮点数
	 * @return
	 */
	public static boolean validFloat(String num, String type) {
		String eL = "";
		if (type.equals("0+"))
			eL = "^\\d+(\\.\\d+)?$";// 非负浮点数
		else if (type.equals("+"))
			eL = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";// 正浮点数
		else if (type.equals("-0"))
			eL = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";// 非正浮点数
		else if (type.equals("-"))
			eL = "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$";// 负浮点数
		else
			eL = "^(-?\\d+)(\\.\\d+)?$";// 浮点数
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(num);
		boolean b = m.matches();
		return b;
	}
	
	/**
	 * 将中文汉字转成UTF8编码
	 * 
	 * @param str
	 * @return
	 */
	public static String toUTF8(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 创建4位随机码
	 * @author xiaokx
	 * @date 2013-9-14下午6:47:22
	 * @return
	 */
	public static String createRandom4(){
		Random r = new Random();
		int x;//定义两变量
        x=r.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
		return x+"";
	}
	
	/**
	 * String转成数字类型
	 * @author xiaokx
	 * @date 2013-9-15上午9:51:48
	 * @param val
	 * @return
	 */
	public static Integer toInt(String val){
		try {
			return Integer.valueOf(val);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Integer toInt0(String val){
		try {
			return Integer.valueOf(val);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * String转成Long类型
	 * @param val
	 * @return
	 */
	public static Long toLong(String val){
		try {
			return Long.valueOf(val);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * String转成Double类型
	 * @param val
	 * @return
	 */
	public static Double toDouble(String val){
		try {
			return Double.valueOf(val);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 得到n位的随机字符串
	 *
	 * @author hqh
	 * createTime:2013-9-16上午9:21:46
	 * e-mail:hqh1689@163.com
	 */
	public static String getRandStr(int n) {
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < n; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		return sRand;
	}
	
	/**
	 * 按指定分隔符将数组转成字符串
	 * @param arr
	 * @param divide
	 * @return
	 */
	public static String arrToStr(String[] arr,String divide){
		if(arr==null)return "";
		String str="";
		for (String string : arr) {
			str+=divide;
			str+=string;
		}
		str = str.replaceFirst(divide, "");
		return str;
	}
	
	
	/**
	 * 过滤emoji表情
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) { 
        if(source != null)
        {
            Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find())
            {
                source = emojiMatcher.replaceAll("*");
                return source ;
            }
        return source;
       }
       return source; 
    }
	
	/**
	 * urlEncoder
	 * @param str
	 * @return
	 */
	public static String urlEncoderUtf8(String str){
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 
	* @Title: generateUniqueCode 
	* @Description:  生成唯一码，例如：订单号 
	* @return String 
	* @throws
	 */
	public static String generateUniqueCode(){
		return StringUtils.generateUniqueCode("101");
	}
	
	/**
	 * 
	* @Title: generateUniqueCode 
	* @Description:  生成唯一码，例如：订单号
	* @machineCode 集群中的机器编码3位
	* @return String 
	* @throws
	 */
	public static String generateUniqueCode(String machineCode){
		int hc = UUID.randomUUID().toString().hashCode();
		if(hc<0){
			hc = -hc;
		}
		// %013d：0表示前面补0，13表示长度为13，d表示参数为正整数 
		String hcStr = machineCode+String.format("%013d", hc);
		return hcStr;
	}
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 22; i++) {
			System.out.println(StringUtils.generateUniqueCode("201"));
		}
		
		System.out.println(Integer.MAX_VALUE);
	}
	
}
