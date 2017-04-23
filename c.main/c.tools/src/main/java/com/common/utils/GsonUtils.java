package com.common.utils;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;  
/**
 * json转化工具类
 * @author xiaokangxin
 *
 */
public class GsonUtils {
	
	private Gson gson = null;
	
	public Gson getInstance(){
		if(gson==null)gson = new Gson();
		return gson;
	}
	
	public static <T> T toObject(String json,Class clazz){
		try {
			return (T) new GsonUtils().getInstance().fromJson(json, clazz);
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 
	* @Title: toObject 
	* @Description:  new TypeToken<List<Map<String,Object>>>(){}
	* @return T 
	* @throws
	 */
	public static <T> T toObject(String json,TypeToken<T> token){
		try {
			return (T) new GsonUtils().getInstance().fromJson(json, token.getType());
		} catch (Exception e) {
		}
		return null;
	}
	
	
	
	/**
	 * 将Map参数转成json格式传输
	 * @param map
	 * @return
	 */
	public static String toJson(Map map){
		return new GsonUtils().getInstance().toJson(map);
	}
	
	/**
	 * 将Object参数转成json格式传输
	 * @param map
	 * @return
	 */
	public static String toJson(Object obj){
		return new GsonUtils().getInstance().toJson(obj);
	}
	
	/**
	 * 将List数组转成json格式传输
	 * @param list
	 * @return
	 */
	public static String toJson(List<Map> list){
		return new GsonUtils().getInstance().toJson(list);
	}

}
