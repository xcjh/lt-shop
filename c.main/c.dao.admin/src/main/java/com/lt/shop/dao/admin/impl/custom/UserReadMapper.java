package com.lt.shop.dao.admin.impl.custom;

import com.lt.shop.dao.admin.entity.def.User;

public interface UserReadMapper {
	
	/**
	 * 根据用户名获取用户
	 * @param uname
	 * @return
	 */
	public User getByUname(String uname);

}
