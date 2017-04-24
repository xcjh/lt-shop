package com.lt.shop.dao.site.impl;

import com.lt.shop.dao.admin.entity.def.Order;

public interface SiteOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}