package com.lt.shop.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lt.shop.service.site.SiteOrderService;

/**
 * 购物车列表
 * 
 * @author do
 *
 */
@Controller
@Scope("prototype")
public class OrderController extends SiteController {

	@Autowired
	SiteOrderService siteOrderService;

	/**
	 * 购物车清单页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/order/confirm", method = RequestMethod.GET)
	public String confirm() {

		return THEME + "/order";
	}

	/**
	 * 添加到购物车
	 * 
	 * @param gid
	 *            商品id
	 * @param num
	 *            数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/order/save", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String add(String goods) {
		int flag = 0; // siteOrderService.add(goods);
		return resp(flag);
	}

	/**
	 * 编辑商品数量
	 * 
	 * @param gid
	 * @param num
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/order/modify", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public String modify(String goods) {
		int flag = 0; // siteOrderService.edit(goods);
		return resp(flag);
	}

}
