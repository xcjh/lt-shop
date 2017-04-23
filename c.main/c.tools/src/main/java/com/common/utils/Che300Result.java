package com.common.utils;

import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: Che300Result 
* @Description: che300查询返回json格式
* @author xiaoli
* @date 2016年12月15日 上午11:42:33 
*
 */

public class Che300Result {
	
	
	private int status;//1 表示成功/0 表示失败
	private String url;//
	private List<Map<String,String>> series_list;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Map<String, String>> getSeries_list() {
		return series_list;
	}
	public void setSeries_list(List<Map<String, String>> series_list) {
		this.series_list = series_list;
	}
	
	
	

}
