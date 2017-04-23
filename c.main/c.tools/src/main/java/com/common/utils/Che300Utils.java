package com.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

/**
 * 
* @ClassName: Che300Utils 
* @Description: 品牌 车系 查询工具类 f974430f0c4641c0498d53b65f7a037e
* environment---1测试，2正式
* @author xiaoli
* @date 2016年12月15日 上午11:15:21 
*
 */
public class Che300Utils {
	
	/**
	 * 
	* @Title: getCarSeriesList 
	* @Description: 返回指定品牌下面的所有车系列表
	* [正式服务器]http://api.che300.com/service/getCarSeriesList
	* [测试服务器]http://testapi.che300.com/service/getCarSeriesList
	* @return List<Map<String,Object>> 
	* @throws
	 */
	public static List<Map<String,Object>> getCarSeriesList(int environment,int brandId){
		String url = "http://testapi.che300.com/service/getCarSeriesList";
		if(environment==2){
			 url = "http://api.che300.com/service/getCarSeriesList";
		}
		url += "?token=f974430f0c4641c0498d53b65f7a037e&brandId="+brandId;
		String result = HttpConnection.sendGet(url, null);
		Che300Result cr =  GsonUtils.toObject(result, Che300Result.class);
		if(cr==null || cr.getStatus()!=1)return null;
		List<Map<String,String>> list = cr.getSeries_list();
		if(list==null)return null;
		List<Map<String,Object>> resutList = new ArrayList<Map<String,Object>>();
		String modelname ="";
		for (Map<String, String> map : list) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			if(modelname.equals(map.get("series_group_name"))){
				continue;
			}
			modelname = map.get("series_group_name");
			map2.put("modelname", modelname);
			List<Map<String,String>> resutList3= new ArrayList<Map<String,String>>();
			for (Map<String, String> map3 : list) {
				if(!modelname.equals(map3.get("series_group_name"))){
					continue;
				}
				Map<String, String> map4 = new HashMap<String, String>();
				map4.put("serialid", map3.get("series_id"));//车系id
				map4.put("serialname", map3.get("series_name"));//车系名字
				resutList3.add(map4);
			}
			
			map2.put("content", resutList3);
			resutList.add(map2);
		}
		
		return resutList;
		
	}
	
	public static void main(String[] args) {
		List<Map<String,Object>> list = Che300Utils.getCarSeriesList(1, 1);
		for (Map<String, Object> map : list) {
			System.out.println(map.get("modelname")+"\t");
			List<Map<String,Object>> list2 = (List<Map<String,Object>>)map.get("content");
			for (Map<String, Object> map2 : list2) {
				System.out.println(map2.get("serialid").toString()+"\t"+map2.get("serialname").toString());
			}
		}
		
	}

}
