package com.common.utils;

import lombok.Data;

/**
 * 
* @ClassName: RespResult 
* @Description: 返回结果
* @author xiaoli
* @date 2016年12月6日 上午11:03:15 
*
 */
@Data
public class RespResult<T> {
	
	private int result;//0:成功，1:session失效，2:错误
	private String message;
	private T data;

}
