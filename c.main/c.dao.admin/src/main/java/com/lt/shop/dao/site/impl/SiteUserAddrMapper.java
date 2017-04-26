package com.lt.shop.dao.site.impl;

import com.lt.shop.dao.admin.entity.custom.UserAddrEntity;

public interface SiteUserAddrMapper {

    int insert(UserAddrEntity record);

    UserAddrEntity selectByPrimaryKey(Long id);
    
    UserAddrEntity selectByUser(Long userId);

    int updateByPrimaryKey(UserAddrEntity record);
}