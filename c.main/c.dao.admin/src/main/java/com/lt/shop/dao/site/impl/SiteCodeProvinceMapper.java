package com.lt.shop.dao.site.impl;

import com.lt.shop.dao.admin.entity.def.CodeProvince;

public interface SiteCodeProvinceMapper {
    int deleteByPrimaryKey(Long provinceid);

    int insert(CodeProvince record);

    int insertSelective(CodeProvince record);

    CodeProvince selectByPrimaryKey(Long provinceid);

    int updateByPrimaryKeySelective(CodeProvince record);

    int updateByPrimaryKey(CodeProvince record);
}