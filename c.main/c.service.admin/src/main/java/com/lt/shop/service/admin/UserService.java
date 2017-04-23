package com.lt.shop.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Constant;
import com.common.service.BaseService;
import com.common.utils.MD5;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.dao.admin.impl.custom.UserReadMapper;

/**
 * 管理后台用户管理service
 * @author xiaoli
 *
 */
@Service
public class UserService extends BaseService {
	
	@Autowired
	UserReadMapper userReadMapper;
	
	/**
	 * 用户登录
	 * @param uname
	 * @param upwd
	 * @return 1成功，2没有店铺帐号，3密码错误,4帐号禁用
	 */
	public int login(String uname,String upwd){
		User user = userReadMapper.getByUname(uname);
		if(user==null)return 2;
		if(!user.getUpwd().equals(MD5.md5(upwd)))return 3;
		if(user.getFlag()==null||user.getFlag()!=1)return 4;
		this.contextService.setObject(Constant.ADMIN_LOGIN_USER, user);
		return 1;
	}
	

}
