package com.lt.shop.dao.admin.impl.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.common.valid.ReqGoodsSearch;

public interface GoodsReadMapper {
	 /**
	  * 根据店铺读取商品列表
	  * @param start
	  * @param num
	  * @param shopId
	  * @return
	  */
    List<Map<String,Object>> list(@Param("start")int start,
			@Param("num")int num,
			@Param("shopId")Long shopId,@Param("params")ReqGoodsSearch params);
    
    /**
     * 根据店铺读取商品总数
     * @param shopId
     * @return
     */
	int listTotal(@Param("shopId")Long shopId,@Param("params")ReqGoodsSearch params);
}