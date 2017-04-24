package com.lt.shop.dao.admin.impl.def;

import com.lt.shop.dao.admin.entity.def.CodeProvince;

public interface CodeProvinceMapper {
    int deleteByPrimaryKey(Long provinceid);

    int insert(CodeProvince record);

    int insertSelective(CodeProvince record);

    CodeProvince selectByPrimaryKey(Long provinceid);

    int updateByPrimaryKeySelective(CodeProvince record);

    int updateByPrimaryKey(CodeProvince record);
}