package com.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
* @ClassName: ExceptionUtils 
* @Description: 异常处理工具类
* @author xiaoli
* @date 2016年12月23日 下午2:05:32 
*
 */
public class ExceptionUtils {

	/**
	 * 
	* @Title: getTrace 
	* @Description: 将异常转为字符串 
	* @return String 
	* @throws
	 */
	public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
}
