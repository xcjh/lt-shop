package com.common.amap;

import java.util.HashMap;
import java.util.Map;

import com.common.utils.GsonUtils;
import com.common.utils.HttpConnection;

/**
 * 
* @ClassName: CloudStorageUtils 
* @Description: 高德云存储API工具类 
* API:	http://lbs.amap.com/yuntu/reference/cloudstorage/
* 云图管理web:http://yuntu.amap.com/datamanager/
* @author xiaoli
* @date 2017年1月17日 下午4:12:58 
*
 */
public class CloudStorageUtils {
	
	public static String key = "daa6e8939540402aafecefc5a87f7a73";//客户唯一标识,用户申请，由高德地图API后台自动分配


	
	/**
	 * 
	* @Title: createTable 
	* @Description: 创建表
	* @return String 
	* @throws
	 */
	public static String createTable(String tableName){
		String url = "http://yuntuapi.amap.com/datamanage/table/create";
		Map<String,String> params = new HashMap<String,String>();
		params.put("key", key);
		params.put("name", tableName);//表的名称
//		params.put("sig", "");//数字签名
		String result = HttpConnection.sendPost(url, params);
		System.out.println(result);
		if(result==null || "".equals(result.trim()))return null;
		RespTable resp = GsonUtils.toObject(result, RespTable.class);
		if(resp.getStatus()==1){
			return resp.getTableid();
		}
		
		return null;
	}
	
	/**
	 * 
	* @Title: createRecord 
	* @Description: 添加记录
	* @return String 
	* @throws
	 */
	public static String addRecord(){
		String url = "http://yuntuapi.amap.com/datamanage/data/create";
		Map<String,String> data = new HashMap<String,String>();
		data.put("_name", "京A12345");
		data.put("_location", "116.544314,39.927928");
		data.put("coordtype", "1");
		data.put("tname", "古天乐");
		data.put("phone", "13811451717");
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("key", key);
		params.put("tableid", "587dd561305a2a6810c038cc");
		params.put("loctype", "1");//定位方式1：经纬度；格式示例：104.394729,31.125698  2：地址；标准格式示例：北京市朝阳区望京阜通东大街6号院3号楼
		params.put("data", GsonUtils.toJson(data));
		System.out.println(String.format("提交参数：%s", params.toString()));
		String result = HttpConnection.sendPost(url, params);
		System.out.println(result);
		if(result==null || "".equals(result.trim()))return null;
		RespRecord resp = GsonUtils.toObject(result, RespRecord.class);
		if(resp.getStatus()==1)return resp.get_id();
		return null;
	}
	
	/**
	 * 
	* @Title: updateRecord 
	* @Description: 更新记录
	* @return String 
	* @throws
	 */
	public static boolean updateRecord(){
		String url = "http://yuntuapi.amap.com/datamanage/data/update";
		Map<String,String> data = new HashMap<String,String>();
		data.put("_id", "3");
		data.put("_name", "京A12345");
		data.put("_location", "116.544314,39.927928");
		data.put("coordtype", "1");
		data.put("tname", "古天乐");
		data.put("phone", "13811451717");
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("key", key);
		params.put("tableid", "587dd561305a2a6810c038cc");
		params.put("loctype", "1");//定位方式1：经纬度；格式示例：104.394729,31.125698  2：地址；标准格式示例：北京市朝阳区望京阜通东大街6号院3号楼
		params.put("data", GsonUtils.toJson(data));
		System.out.println(String.format("提交参数：%s", params.toString()));
		String result = HttpConnection.sendPost(url, params);
		System.out.println(result);
		if(result==null || "".equals(result.trim()))return false;
		RespRecord resp = GsonUtils.toObject(result, RespRecord.class);
		if(resp.getStatus()==1)return true;
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(CloudStorageUtils.updateRecord());
	}

}
