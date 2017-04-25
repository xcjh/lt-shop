package com.lt.shop.dao.admin.impl.def;

import com.lt.shop.dao.admin.entity.def.UserAddr;

public interface UserAddrMapper {
    int insert(UserAddr record);

    int insertSelective(UserAddr record);
}