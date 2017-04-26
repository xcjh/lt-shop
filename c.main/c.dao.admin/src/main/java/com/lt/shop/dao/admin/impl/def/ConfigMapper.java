package com.lt.shop.dao.admin.impl.def;

import com.lt.shop.dao.admin.entity.def.Config;

public interface ConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}