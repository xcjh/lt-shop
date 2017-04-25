package com.lt.shop.service.site;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Constant;
import com.common.service.BaseService;
import com.common.utils.StringUtils;
import com.lt.shop.dao.admin.entity.custom.UserAddrEntity;
import com.lt.shop.dao.admin.entity.def.Order;
import com.lt.shop.dao.admin.entity.def.OrderItem;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.dao.site.impl.SiteOrderItemMapper;
import com.lt.shop.dao.site.impl.SiteOrderMapper;
import com.lt.shop.dao.site.impl.SiteUserAddrMapper;

/**
 * 购物车业务处理
 * 
 * @author do
 *
 */
@Service
public class SiteOrderService extends BaseService {

	@Autowired
	SiteOrderMapper siteOrderMapper;

	@Autowired
	SiteOrderItemMapper siteOrderItemMapper;

	@Autowired
	SiteUserAddrMapper siteUserAddrMapper;

	public Order getDefaultOrder() {
		Order order = new Order();
		// sn
		order.setOrderSn(StringUtils.generateUniqueCode());
		// status
		order.setOrderStatus(1); // 0已取消,1新订单,2已支付,3已发货,4已收货,5已完成
		order.setPayStatus(1); // 1未付款，2已付款，3已退款
		order.setDeliverStatus(1); // 1未发货，2已发货，3已退货
		// others
		order.setAddTime(new Date().getTime());
		order.setAmountRefund(new BigDecimal(0));
		return order;
	}

	private void putAddr(Order order, Long addrId) {
		UserAddrEntity addr = siteUserAddrMapper.selectByPrimaryKey(addrId);
		if (addr == null) {
			// throw new Exception("addr is null.");
			return;
		}
		order.setProvince(addr.getProvince());
		order.setCity(addr.getCity());
		order.setDistrict(addr.getDistrict());
		order.setReceiptName(addr.getReceiptName());
		order.setReceiptPhone(addr.getReceiptPhone());
	}

	/**
	 * 添加
	 * 
	 * @param gid
	 * @param num
	 * @return
	 */
	public int add(BigDecimal amount, BigDecimal freight, String[][] items) {
		User user = (User) contextService.getObject(Constant.SITE_LOGIN_USER);
		Order order = getDefaultOrder();
		order.setUserId(user.getId());
		order.setAmount(amount);
		order.setFreight(freight);
		Long orderId = siteOrderMapper.insert(order);
		if (items != null && items.length > 0) {
			OrderItem oi = null;
			int result = 0;
			for (String[] item : items) {
				oi = new OrderItem();
				oi.setGoodsId(new Long(item[0]));
				oi.setPrice(new BigDecimal(item[1]));
				oi.setNum(new Integer(item[2]));
				oi.setOrderId(orderId);
				oi.setUserId(user.getId());
				oi.setAddTime(new Date().getTime());
				result = siteOrderItemMapper.insert(oi);
				if (result == 0) {
					// throw new Exception("order item add fial.");
				}
			}
		} else {
			// throw new Exception("order item is null.");
		}
		// 清空购物车
		
		return 1; // ?
	}

	/**
	 * 编辑商品数量
	 * 
	 * @param gid
	 * @param num
	 * @return
	 */
	public int edit(Long orderId, Long addrId) {
		Order order = siteOrderMapper.selectByPrimaryKey(orderId);
		putAddr(order, addrId);
		// 预留以后有优惠券
		siteOrderMapper.updateOrder(order);
		return 0;
	}

}
