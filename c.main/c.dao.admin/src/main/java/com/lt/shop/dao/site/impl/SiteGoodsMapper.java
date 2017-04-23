package com.lt.shop.dao.site.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * 前台商品数据层
 * @author Edwin
 *
 */
public interface SiteGoodsMapper {
	 /**
	  * 根据店铺读取商品列表
	  * @param start
	  * @param num
	  * @param shopId
	  * @return
	  */
    List<Map<String,Object>> list(@Param("start")int start,
			@Param("num")int num,
			@Param("shopId")Long shopId);
    
    /**
     * 根据店铺读取商品总数
     * @param shopId
     * @return
     */
	int listTotal(@Param("shopId")Long shopId);
}