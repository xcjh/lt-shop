package com.lt.shop.dao.site.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SiteMemberOrderMapper {
	
	/**
	  * 根据用户读取订单列表
	  * @param start
	  * @param num
	  * @param shopId
	  * @return
	  */
 List<Map<String,Object>> list(@Param("start")int start,
			@Param("num")int num,@Param("userId")Long userId);
 
 /**
  * 根据用户读取订单总数
  * @param shopId
  * @return
  */
	int listTotal(@Param("userId")Long userId);
	
	
	/**
	 * 获取订单明细列表
	 * @param orderId
	 * @return
	 */
	List<Map<String,Object>> listOrderItems(Long orderId);

}
