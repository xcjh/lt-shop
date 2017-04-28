package com.lt.shop.dao.site.impl;

import com.lt.shop.dao.admin.entity.def.Order;

public interface SiteOrderMapper {
	int deleteByPrimaryKey(Long id);

	Long insert(Order record);

	int insertSelective(Order record);

	Order selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Order record);

	int updateByPrimaryKey(Order record);

	int updateOrder(Order record);

	int updateOrderStatus(Integer status, Long id);

	int updatePayStatus(Integer status, Long id);
}