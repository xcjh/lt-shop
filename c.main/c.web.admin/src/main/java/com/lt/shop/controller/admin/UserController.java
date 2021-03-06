package com.lt.shop.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.common.utils.StringUtils;
import com.common.valid.ReqUserEditPwd;
import com.common.valid.ReqUserSearch;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.service.admin.UserService;

/*
 * 用户列表
 */
@Controller
@Scope("prototype")
public class UserController extends AdminController {
	
	@Autowired
	UserService userService;
	
	/**
	 * 用户列表/搜索
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/u/l{page}",method=RequestMethod.GET)
	public String list(@PathVariable String page,ReqUserSearch params){
		//获取页码
		Integer pageNo = StringUtils.toInt(page);
		pageNo=pageNo==null?1:pageNo;
		pageNo=pageNo<1?1:pageNo;
		this.webpage = userService.list(pageNo, pageSize,params);
		loadPagerHtml();
		request.setAttribute("pager", webpage);
		request.setAttribute("pageNo",pageNo);
		request.setAttribute("params",params);
		return THEME+"/user/list";
	}
	
	/**
	 * 用户停用、启用
	 * @param id 用户ID
	 * @param status 新状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/u/m/{id}/{status}",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String modfiyStatus(@PathVariable Long id,@PathVariable Integer status){
		if(id==null){
			return resp(2,"用户ID不能为空");
		}
		if(status==null){
			return resp(2,"新状态不能为空");
		}else if(status<0 || status>3){
			return resp(2,"新状态不正确");
		}
		return resp(userService.modfiyStatus(id, status));
	}
	
	/**
	 * 登录用户修改自己的密码
	 * @return
	 */
	@RequestMapping(value="/u/editpwd",method=RequestMethod.GET)
	public String editpwd(){
		User user = (User)contextService.getObject(Constant.ADMIN_LOGIN_USER);
		request.setAttribute("user", user);
		return THEME + "/user/editpwd"; 
	}
	
	/**
	 * 保存密码修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/u/editpwds",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String editpwds(@Valid ReqUserEditPwd req,BindingResult result){
		if(result.hasErrors()){
			return resp(2,getMsg(result));
		}
		if(!req.getUpwd().equals(req.getUpwd2())){
			return resp(2,"两次密码不一致");
		}
		int flag = userService.editPwd(req);
		if(flag!=1){
			return resp(2,"修改密码失败");
		}
		return resp(1);
	}

}
