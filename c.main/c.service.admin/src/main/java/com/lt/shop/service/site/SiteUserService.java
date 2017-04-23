package com.lt.shop.service.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Constant;
import com.common.service.BaseService;
import com.common.utils.MD5;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.dao.admin.impl.def.UserMapper;
import com.lt.shop.dao.site.impl.SiteUserMapper;

/**
 * 前端用户业务处理
 * @author xiaoli
 *
 */
@Service
public class SiteUserService extends BaseService {
	
	@Autowired
	SiteUserMapper siteUserMapper;
	
	@Autowired
	UserMapper userMapper;
	
	/**
	 * 用户注册
	 * @param uname
	 * @param upwd
	 * @return 1成功，2已存在，
	 */
	public int reg(String uname,String upwd){
		User user = siteUserMapper.getByMobile(uname);
		if(user!=null){
			return 2;//已存在
		}
		user = new User();
		user.setRegtime(System.currentTimeMillis());
		user.setUname(uname);
		user.setMobile(uname);
		user.setUpwd(MD5.md5(upwd));
		user.setFlag(1);
		int result =  userMapper.insert(user);
		if(result==1){
			this.contextService.setObject("site_login", user);
		}
		return result;
	}
	
	/**
	 * 用户登录
	 * @param uname
	 * @param upwd
	 * @return 1成功，2不存在，
	 */
	public int login(String uname,String upwd){
		User user = siteUserMapper.getByMobile(uname);
		if(user==null){
			return 2;//用户不存在
		}
		if(!user.getUpwd().equals(MD5.md5(upwd))){
			return 3;//密码不正确
		}
		this.contextService.setObject(Constant.SITE_LOGIN_USER, user);
		return 1;
	}
}
