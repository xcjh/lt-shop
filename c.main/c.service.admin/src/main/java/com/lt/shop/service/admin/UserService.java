package com.lt.shop.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Constant;
import com.common.service.BaseService;
import com.common.utils.MD5;
import com.common.utils.Pager;
import com.common.valid.ReqUserEditPwd;
import com.common.valid.ReqUserSearch;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.dao.admin.impl.custom.UserReadMapper;
import com.lt.shop.dao.admin.impl.custom.UserWriteMapper;
import com.lt.shop.dao.admin.impl.def.UserMapper;

/**
 * 管理后台用户管理service
 * @author xiaoli
 *
 */
@Service
public class UserService extends BaseService {
	
	@Autowired
	UserReadMapper userReadMapper;
	
	@Autowired
	UserWriteMapper userWriteMapper;
	
	@Autowired
	UserMapper userMapper;
	
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
	
	
	/**
	 * 用户分页列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager list(int pageNo, int pageSize,ReqUserSearch params){
		List<Map<String,Object>> datas = userReadMapper.list((pageNo - 1) * pageSize, pageSize,params);
		int total = userReadMapper.listTotal(params);
		return new Pager(datas, total, pageNo, pageSize);
	}
	
	/**
	 * 修改用户状态
	 * @param id 用户id
	 * @param status 新状态
	 * @return
	 */
	public int modfiyStatus(Long id,Integer status){
		return userWriteMapper.modfiyStatus(id, status);
	}
	
	/**
	 * 用户修改密码
	 * @param req
	 * @return
	 */
	public int editPwd(ReqUserEditPwd req){
		User user = userMapper.selectByPrimaryKey(req.getId());
		user.setUpwd(MD5.md5(req.getUpwd()));
		int result = userMapper.updateByPrimaryKey(user);
		return result;
	}
	

}
