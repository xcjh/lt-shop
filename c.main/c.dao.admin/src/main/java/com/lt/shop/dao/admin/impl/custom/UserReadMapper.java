package com.lt.shop.dao.admin.impl.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.common.valid.ReqUserSearch;
import com.lt.shop.dao.admin.entity.def.User;

public interface UserReadMapper {
	
	/**
	 * 根据用户名获取用户
	 * @param uname
	 * @return
	 */
	public User getByUname(String uname);
	
	/**
	  * 根据店铺读取商品列表
	  * @param start
	  * @param num
	  * @param shopId
	  * @return
	  */
   List<Map<String,Object>> list(@Param("start")int start,
			@Param("num")int num,@Param("params")ReqUserSearch params);
   
   /**
    * 根据店铺读取商品总数
    * @param shopId
    * @return
    */
	int listTotal(@Param("params")ReqUserSearch params);

}
