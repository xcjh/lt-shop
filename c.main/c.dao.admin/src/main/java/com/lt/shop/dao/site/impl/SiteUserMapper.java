package com.lt.shop.dao.site.impl;

import com.lt.shop.dao.admin.entity.def.User;

/**
 * 前端用户数据层
 * @author xiaoli
 *
 */
public interface SiteUserMapper {
	
	public User getByMobile(String moible);

}
