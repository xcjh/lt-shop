package com.lt.shop.controller.site;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.lt.shop.dao.admin.entity.custom.UserAddrEntity;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.service.site.SiteCartService;
import com.lt.shop.service.site.SiteOrderService;
import com.lt.shop.service.site.SiteUserAddrService;

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
	SiteCartService siteCartService;

	@Autowired
	SiteOrderService siteOrderService;

	@Autowired
	SiteUserAddrService siteUserAddrService;

	/**
	 * 购物车清单页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/order/confirm", method = RequestMethod.GET)
	public String confirm() {
		User user = (User) contextService.getObject(Constant.SITE_LOGIN_USER);
		Map<String, Object> map = siteCartService.listCart();
		request.setAttribute("map", map);
		UserAddrEntity addr = siteUserAddrService.getDefault(user.getId());
		request.setAttribute("addr", addr);
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
	public String add(String receiptName, String receiptPhone, Long provinceId, Long cityId, Long districtId,
			String address, String[] good, BigDecimal amountTotal, BigDecimal freightTotal) {
		int flag = siteOrderService.add(receiptName, receiptPhone, provinceId, cityId, districtId, address, good,
				amountTotal, freightTotal);
		return resp(flag);
	}

}
