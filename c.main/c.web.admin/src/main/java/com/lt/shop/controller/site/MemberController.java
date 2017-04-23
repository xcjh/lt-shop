package com.lt.shop.controller.site;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.Constant;
import com.lt.shop.dao.admin.entity.def.User;

/**
 * 会员中心
 * @author xiaoli
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/m")
public class MemberController extends SiteController {

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		User user = (User)contextService.getObject(Constant.SITE_LOGIN_USER);
		if(user==null){
			return "redirect:/login";
		}
		request.setAttribute("user", user);
		return THEME+"/member/index";
	}
}
