package com.lt.shop.controller.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户中心主页
 * @author xiaoli
 *
 */
@Controller
@Scope("prototype")
public class MainController extends AdminController {
	
	
	/**
	 * 用户中心管理界面首页
	 * @return
	 */
	@RequestMapping(value="/main",method=RequestMethod.GET)
	public String main(){
		return THEME+"/main";
	}
	
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public String welcome(){
		return THEME+"/welcome";
	}
	
}
