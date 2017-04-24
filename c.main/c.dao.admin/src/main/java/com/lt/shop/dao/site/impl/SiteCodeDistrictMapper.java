package com.lt.shop.dao.site.impl;

import com.lt.shop.dao.admin.entity.def.CodeDistrict;

public interface SiteCodeDistrictMapper {
    int deleteByPrimaryKey(Long districtid);

    int insert(CodeDistrict record);

    int insertSelective(CodeDistrict record);

    CodeDistrict selectByPrimaryKey(Long districtid);

    int updateByPrimaryKeySelective(CodeDistrict record);

    int updateByPrimaryKey(CodeDistrict record);
}