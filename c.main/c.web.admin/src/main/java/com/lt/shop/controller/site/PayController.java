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
public class PayController extends SiteController {

	/**
	 * 支付
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public String confirm() {
		return THEME + "/pay";
	}
}
