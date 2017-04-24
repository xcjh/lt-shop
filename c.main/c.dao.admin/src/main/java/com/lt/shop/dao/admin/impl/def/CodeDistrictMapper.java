package com.lt.shop.dao.admin.impl.def;

import com.lt.shop.dao.admin.entity.def.CodeDistrict;

public interface CodeDistrictMapper {
    int deleteByPrimaryKey(Long districtid);

    int insert(CodeDistrict record);

    int insertSelective(CodeDistrict record);

    CodeDistrict selectByPrimaryKey(Long districtid);

    int updateByPrimaryKeySelective(CodeDistrict record);

    int updateByPrimaryKey(CodeDistrict record);
}