package com.lt.shop.dao.site.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.common.valid.RespCart;
import com.lt.shop.dao.admin.entity.def.Cart;

/**
 * 购物车数据层
 * @author xiaoli
 *
 */
public interface SiteCartMapper {
	
	/**
	 * 获取购物车列表
	 * @param userId
	 * @return
	 */
	List<RespCart> list(Long userId);
	
	/**
	 * 根据商品id,用户id获取商品购物车对象
	 * @param goodsId
	 * @param userId
	 * @return
	 */
	Cart get(@Param("goodsId")Long goodsId,@Param("userId")Long userId);

}
