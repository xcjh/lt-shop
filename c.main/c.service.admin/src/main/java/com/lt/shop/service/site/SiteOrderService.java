package com.lt.shop.service.site;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.Constant;
import com.common.service.BaseService;
import com.common.utils.CurrencyUtils;
import com.common.valid.RespCart;
import com.lt.shop.dao.admin.entity.def.Cart;
import com.lt.shop.dao.admin.entity.def.Order;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.dao.site.impl.SiteOrderItemMapper;
import com.lt.shop.dao.site.impl.SiteOrderMapper;

/**
 * 购物车业务处理
 * @author do
 *
 */
@Service
public class SiteOrderService extends BaseService {
	
	@Autowired
	SiteOrderMapper siteOrderMapper;
	
	@Autowired
	SiteOrderItemMapper siteOrderItemMapper;
	
	
	
	/**
	 * 添加
	 * @param gid
	 * @param num
	 * @return
	 */
	public int add(String goods){
		User user = (User)contextService.getObject(Constant.SITE_LOGIN_USER);
		Order order = new Order();
		// auth
		order.setUserId(user.getId());
		// status 
		return 0;
	}
	
	/**
	 * 编辑商品数量
	 * @param gid
	 * @param num
	 * @return
	 */
	public int edit(String goods){
		return 0;
	}

}
