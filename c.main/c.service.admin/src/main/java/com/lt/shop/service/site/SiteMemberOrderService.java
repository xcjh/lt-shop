package com.lt.shop.service.site;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Constant;
import com.common.service.BaseService;
import com.common.utils.Pager;
import com.lt.shop.dao.admin.entity.def.Order;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.dao.admin.impl.def.OrderMapper;
import com.lt.shop.dao.site.impl.SiteMemberOrderMapper;

/**
 * 会员中心订单业务处理
 * @author xiaoli
 *
 */
@Service
public class SiteMemberOrderService extends BaseService {
	
	@Autowired
	SiteMemberOrderMapper siteMemberOrderMapper;
	
	@Autowired
	OrderMapper orderMapper;
	
	/**
	 * 订单分页列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager list(int pageNo, int pageSize){
		User user = (User)contextService.getObject(Constant.SITE_LOGIN_USER);
		List<Map<String,Object>> datas = siteMemberOrderMapper.list((pageNo - 1) * pageSize, pageSize,user.getId());
		int total = siteMemberOrderMapper.listTotal(user.getId());
		return new Pager(datas, total, pageNo, pageSize);
	}
	
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	public Map<String,Object> detail(Long id){
		Map<String,Object> map = new HashMap<String, Object>();
		Order order = orderMapper.selectByPrimaryKey(id);
		map.put("order", order);
		map.put("list", siteMemberOrderMapper.listOrderItems(id));
		return map;
	}
	
	/**
	 * 取消订单
	 * @param id
	 * @return
	 */
	public int cancelOrder(Long id){
		User user = (User)contextService.getObject(Constant.SITE_LOGIN_USER);
		Order order = orderMapper.selectByPrimaryKey(id);
		if(user.getId().longValue()!=order.getUserId().longValue())return 0;
		order.setOrderStatus(0);
		return orderMapper.updateByPrimaryKey(order);
	}

}
