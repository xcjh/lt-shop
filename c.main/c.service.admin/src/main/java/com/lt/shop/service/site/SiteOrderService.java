package com.lt.shop.service.site;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Constant;
import com.common.service.BaseService;
import com.common.utils.StringUtils;
import com.lt.shop.dao.admin.entity.custom.UserAddrEntity;
import com.lt.shop.dao.admin.entity.def.Order;
import com.lt.shop.dao.admin.entity.def.OrderItem;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.dao.site.impl.SiteCartMapper;
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
	SiteCartMapper siteCartMapper;

	@Autowired
	SiteOrderMapper siteOrderMapper;

	@Autowired
	SiteOrderItemMapper siteOrderItemMapper;

	@Autowired
	SiteUserAddrMapper siteUserAddrMapper;
	
	@Autowired
	SiteUserAddrService siteUserAddrService;

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

	private void putAddr(Order order, User user, String receiptName, String receiptPhone, Long provinceId, Long cityId,
			Long districtId, String address) {
		UserAddrEntity entity = siteUserAddrService.get(user.getId(), receiptName, receiptPhone, provinceId, cityId, districtId,
				address);
		order.setReceiptName(receiptName);
		order.setReceiptPhone(receiptPhone);
		order.setProvinceId(provinceId);
		// order.setProvince(addr.getProvince());
		order.setCityId(cityId);
		// order.setCity(addr.getCity());
		order.setDistrictId(districtId);
		// order.setDistrict(addr.getDistrict());
		order.setAddress(address);
	}

	/**
	 * 添加
	 * 
	 * @param gid
	 * @param num
	 * @return
	 */
	public Order add(String receiptName, String receiptPhone, Long provinceId, Long cityId, Long districtId,
			String address, String[] goods, BigDecimal amountTotal, BigDecimal freightTotal) {
		User user = (User) contextService.getObject(Constant.SITE_LOGIN_USER);
		Order order = getDefaultOrder();
		order.setUserId(user.getId());
		order.setAmount(amountTotal);
		order.setFreight(freightTotal);
		putAddr(order, user, receiptName, receiptPhone, provinceId, cityId, districtId, address);
		siteOrderMapper.insert(order);
		if (goods != null && goods.length > 0) {
			OrderItem oi = null;
			int result = 0;
			List<String> cartIds = new ArrayList<>();
			for (String good : goods) {
				String[] items = good.split("_");
				cartIds.add(items[0]);
				oi = new OrderItem();
				oi.setGoodsId(new Long(items[1]));
				oi.setNum(new Integer(items[2]));
				oi.setPrice(new BigDecimal(items[3]));
				oi.setOrderId(order.getId());
				oi.setUserId(user.getId());
				oi.setAddTime(new Date().getTime());
				result = siteOrderItemMapper.insert(oi);
				if (result == 0) {
					// throw new Exception("order item add fial.");
				}
			}
			siteCartMapper.batchDelete(cartIds); // 清空购物车
		} else {
			// throw new Exception("order item is null.");
		}
		return order; // ?
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Order getOrder(Long id) {
		return siteOrderMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 
	 * @param status 0已取消,1新订单,2已支付,3已发货,4已收货,5已完成
	 * @param id
	 */
	public void updateOrderStatus(int status, Long id) {
		siteOrderMapper.updateOrderStatus(status, id);
	}
	/**
	 * 
	 * @param status 1未付款，2已付款，3已退款
	 * @param id
	 */
	public void updatePayStatus(int status, Long id) {
		siteOrderMapper.updatePayStatus(status, id);
	}
}
