package com.lt.shop.dao.site.impl;

import com.lt.shop.dao.admin.entity.def.CodeCity;

public interface SiteCodeCityMapper {
    int deleteByPrimaryKey(Long cityid);

    int insert(CodeCity record);

    int insertSelective(CodeCity record);

    CodeCity selectByPrimaryKey(Long cityid);

    int updateByPrimaryKeySelective(CodeCity record);

    int updateByPrimaryKey(CodeCity record);
}