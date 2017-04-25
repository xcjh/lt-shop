package com.lt.shop.dao.site.impl;

import com.lt.shop.dao.admin.entity.custom.UserAddrEntity;
import com.lt.shop.dao.admin.entity.def.UserAddr;

public interface SiteUserAddrMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAddr record);

    int insertSelective(UserAddr record);

    UserAddrEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAddr record);

    int updateByPrimaryKey(UserAddr record);
}