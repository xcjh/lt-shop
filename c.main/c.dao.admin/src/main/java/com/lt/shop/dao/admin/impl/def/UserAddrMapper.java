package com.lt.shop.dao.admin.impl.def;

import com.lt.shop.dao.admin.entity.def.UserAddr;

public interface UserAddrMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAddr record);

    int insertSelective(UserAddr record);

    UserAddr selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAddr record);

    int updateByPrimaryKey(UserAddr record);
}