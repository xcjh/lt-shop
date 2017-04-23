package com.common.amap;

/**
 * 
 * @ClassName: RespTable
 * @Description: 高德云存储api返回参数
 * @author xiaoli
 * @date 2017年1月17日 下午4:19:33
 *
 */
public class RespTable {

	/**
	 * 返回状态 取值规则： 1：成功； 0：失败，未知原因； -11：失败，已存在相同名称表 -21：失败，已创建表达到最大数据
	 */
	int status;

	/**
	 * status = 1，info返回“ok”
	 * status = 0，info返回以下值 -11: 失败，已存在相同名称表 -21：失败，已创建表达到最大数目 更多返回值查看： 错误码说明
	 */
	String info;
	
	
	/**
	 * 生成的数据表的唯一标识（该参数是发起数据存储API以及检索API的必填参数）
	 */
	String tableid;


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public String getTableid() {
		return tableid;
	}


	public void setTableid(String tableid) {
		this.tableid = tableid;
	}
	
	

}
