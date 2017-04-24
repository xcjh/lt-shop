package com.lt.shop.controller.admin;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.Constant;
import com.common.utils.AESUtils;
import com.common.utils.HttpUtils;
import com.common.utils.StringUtils;
import com.common.valid.ReqLogin;
import com.lt.shop.service.admin.UserService;

/**
 * 用户中心登录控制器
 * @author xiaoli
 *
 */
@Controller
@Scope("prototype")
public class LoginController extends AdminController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		String val = HttpUtils.getCookieValue(request, "LTACCOUNTS");
		if(!StringUtils.isEmpty(val)){
			try {
				val = URLDecoder.decode(val, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String keys = AESUtils.getInstance().decrypt(val);
			keys = keys==null?"":keys;
			String [] arr = keys.split("###-###");
			if(arr.length==2){
				request.setAttribute("uname", arr[0]);
				request.setAttribute("upwd", arr[1]);
				request.setAttribute("online", 1);
			}
		}
		return THEME+"/login";
	}
	
	/**
	 * 用户登录提交post
	 * @param req
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/loginp",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String loginpost (@Valid ReqLogin req,BindingResult result){
		if(result.hasErrors()){
			return resp(2,getMsg(result));
		}
		int flag =userService.login(req.getUname(), req.getUpwd()) ;//1登录成功，2用户不存在,3密码不匹配,4帐号已禁用
		if(flag==1){
			if(req.getOnline()==1){
				//记住登录帐号，密码
				String keys = AESUtils.getInstance().encrypt(req.getUname()+"###-###"+req.getUpwd());
				HttpUtils.addCookie(response, "LTACCOUNTS", keys, 30*24*60*60);//记录一个月
			}else{
				HttpUtils.addCookie(response, "LTACCOUNTS", "", 0);
			}
			return resp(1);
		}else if(flag==2){
			return resp(2,"帐号不存在");
		}
		else if(flag==3){
			return resp(2,"密码不匹配");
		}
		else if(flag==4){
			return resp(2,"帐号已禁用");
		}
		return resp(2,"登录失败");
		
	}
	
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView logout(){
		contextService.deleteKey(Constant.ADMIN_LOGIN_USER);
		return new ModelAndView("redirect:login");
	}

}
