package com.lt.shop.controller.site;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utils.StringUtils;
import com.lt.shop.controller.site.vail.VailLogin;
import com.lt.shop.service.site.SiteUserService;

/**
 * 前台登录
 * @author xiaoli
 *
 */
@Controller
@Scope("prototype")
public class SiteLoginController extends SiteController {
	
	@Autowired
	SiteUserService siteUserService;
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		String furl = request.getParameter("furl");
		request.setAttribute("furl", furl);
		return THEME+"/login";
	}
	
	@ResponseBody
	@RequestMapping(value="/login/save",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String loginSave(@Valid VailLogin req,BindingResult result){
		if(result.hasErrors()){
			return resp(2,getMsg(result));
		}
		String furl = request.getParameter("furl");
		int flag = siteUserService.login(req.getUname(), req.getUpwd());
		if(flag==1&&furl!=null){
			return resp(1,furl);
		}
		return resp(flag);
	}
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping(value="/reg",method=RequestMethod.GET)
	public String reg(){
		return THEME+"/reg";
	}
	
	/**
	 * 短信验证码获取
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/vcode/{mobile}",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String vcode(@PathVariable String mobile){
		if(mobile==null)return resp(2,"请输入手机号");
		if(!StringUtils.validMobile(mobile))return resp(2,"您的手机号不正确");
		String vcode = StringUtils.createRandom4();
		contextService.setObject("reg_vcode", vcode);
		return resp(1,vcode);
	}
	
	/**
	 * 注册保存
	 * @param uname
	 * @param upwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/reg/save",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String regSave(@Valid VailLogin req,BindingResult result){
		if(result.hasErrors()){
			return resp(2,getMsg(result));
		}
		int flag = siteUserService.reg(req.getUname(), req.getUpwd());
		return resp(flag);
	}
	
}
