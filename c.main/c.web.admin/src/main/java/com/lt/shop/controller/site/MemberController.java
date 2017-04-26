package com.lt.shop.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.service.site.SiteMemberOrderService;

/**
 * 会员中心
 * @author xiaoli
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/m")
public class MemberController extends SiteController {
	
	@Autowired
	SiteMemberOrderService siteMemberOrderService;

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
	
	/**
	 * 订单列表
	 * @return
	 */
	@RequestMapping(value="/order",method=RequestMethod.GET)
	public String order(){
		this.webpage = siteMemberOrderService.list(1, pageSize);
		request.setAttribute("webpage", webpage);
		return THEME+"/member/order_list";
	}
	
	/**
	 * 订单详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/od-{id}",method=RequestMethod.GET)
	public String orderDetail(@PathVariable Long id){
		request.setAttribute("detail", siteMemberOrderService.detail(id));
		return THEME+"/member/order_detail";
	}
	
	/**
	 * 取消订单
	 * @param id 订单主键
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/odc-{id}",method=RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String cancelOrder(@PathVariable Long id){
		return resp(siteMemberOrderService.cancelOrder(id));
		
	}
	
	
	/**
	 * 联系我们
	 * @return
	 */
	@RequestMapping(value="/contact")
	public String contact(){
		User user = (User)contextService.getObject(Constant.SITE_LOGIN_USER);
		request.setAttribute("user", user);
		return THEME+"/member/contact";
	}
	
	/**
	 * 用户协议
	 * @return
	 */
	@RequestMapping(value="/protocol")
	public String protocol(){
		User user = (User)contextService.getObject(Constant.SITE_LOGIN_USER);
		request.setAttribute("user", user);
		return THEME+"/member/protocol";
	}
}
