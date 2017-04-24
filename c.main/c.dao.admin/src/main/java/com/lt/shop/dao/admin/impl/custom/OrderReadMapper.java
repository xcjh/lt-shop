package com.lt.shop.dao.admin.impl.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.common.valid.ReqOrderSearch;

public interface OrderReadMapper {
	
	/**
	  * 订单列表
	  * @param start
	  * @param num
	  * @param shopId
	  * @return
	  */
  List<Map<String,Object>> list(@Param("start")int start,
			@Param("num")int num,@Param("params")ReqOrderSearch params);
  
  /**
   * 订单总数
   * @param shopId
   * @return
   */
	int listTotal(@Param("params")ReqOrderSearch params);

}
