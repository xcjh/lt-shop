package com.lt.shop.dao.site.impl;

import com.lt.shop.dao.admin.entity.def.UserAddr;

public interface SiteUserAddrMapper {
    int insert(UserAddr record);

    int insertSelective(UserAddr record);
}