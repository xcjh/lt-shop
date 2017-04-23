package com.lt.shop.controller.site;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.common.utils.GsonUtils;
import com.lt.shop.service.site.SiteCartService;

/**
 * 购物车列表
 * @author xiaoli
 *
 */
@Controller
@Scope("prototype")
public class CartController extends SiteController {
	
	@Autowired
	SiteCartService siteCartService;
	
	/**
	 * 购物车清单页
	 * @return
	 */
	@RequestMapping(value="/cart",method=RequestMethod.GET)
	public String list(){
		Object obj = contextService.getObject(Constant.SITE_LOGIN_USER);
		if(obj==null){
			return "redirect:/login";
		}
		Map<String,Object> map = siteCartService.listCart();
		request.setAttribute("map", map);
		return THEME+"/cart";
	}
	
	/**
	 * 添加到购物车
	 * @param gid 商品id
	 * @param num 数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/cart/save",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String add(Long gid,Integer num){
		Object obj = contextService.getObject(Constant.SITE_LOGIN_USER);
		if(gid==null){
			return resp(2,"请选择要购买的商品");
		}
		if(obj==null){
			return resp(3,"您还未登录");
		}
		num=num==null?1:num;
		num=num<1?1:num;
		int flag = siteCartService.add(gid, num);
		return resp(flag);
	}
	
	/**
	 * 编辑商品数量
	 * @param gid
	 * @param num
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/cart/modify",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String modify(Long gid,Integer num){
		Object obj = contextService.getObject(Constant.SITE_LOGIN_USER);
		if(gid==null){
			return resp(2,"请选择要购买的商品");
		}
		if(obj==null){
			return resp(3,"您还未登录");
		}
		if(num==null||num.intValue()<1){
			return resp(2,"商品数量不正确");
		}
		int flag = siteCartService.edit(gid, num);
		if(flag==1){
			return resp(1,siteCartService.listCart());
		}
		return resp(flag);
	}

}
