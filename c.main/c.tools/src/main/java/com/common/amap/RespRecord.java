package com.common.amap;

/**
 * 
 * @ClassName: RespRecord
 * @Description: 高德云存储api返回参数
 * @author xiaoli
 * @date 2017年1月17日 下午4:19:33
 *
 */
public class RespRecord {

	/**
	 * 返回状态 取值规则：1：成功；0：失败
	 */
	int status;

	/**
	 * status = 1，info返回“ok”
	 * 
	 * status = 0，info返回参见:
	 */
	String info;

	/**
	 * 成功创建的数据id
	 */
	String _id;

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

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	

}
