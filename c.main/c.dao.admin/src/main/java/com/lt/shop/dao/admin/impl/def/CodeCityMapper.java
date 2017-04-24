package com.lt.shop.dao.admin.impl.def;

import com.lt.shop.dao.admin.entity.def.CodeCity;

public interface CodeCityMapper {
    int deleteByPrimaryKey(Long cityid);

    int insert(CodeCity record);

    int insertSelective(CodeCity record);

    CodeCity selectByPrimaryKey(Long cityid);

    int updateByPrimaryKeySelective(CodeCity record);

    int updateByPrimaryKey(CodeCity record);
}