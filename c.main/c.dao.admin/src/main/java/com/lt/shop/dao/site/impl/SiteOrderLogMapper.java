package com.lt.shop.dao.site.impl;

import com.lt.shop.dao.admin.entity.def.OrderLog;

public interface SiteOrderLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderLog record);

    int insertSelective(OrderLog record);

    OrderLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderLog record);

    int updateByPrimaryKey(OrderLog record);
}