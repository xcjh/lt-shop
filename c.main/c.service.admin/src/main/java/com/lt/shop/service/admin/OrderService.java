package com.lt.shop.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.service.BaseService;
import com.common.utils.Pager;
import com.common.valid.ReqOrderSearch;
import com.lt.shop.dao.admin.entity.def.Order;
import com.lt.shop.dao.admin.impl.custom.OrderReadMapper;
import com.lt.shop.dao.admin.impl.def.OrderMapper;

/**
 * 管理后台订单管理业务处理
 * @author xiaoli
 *
 */
@Service
public class OrderService extends BaseService {
	
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	OrderReadMapper orderReadMapper;
	
	/**
	 * 用户分页列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager list(int pageNo, int pageSize,ReqOrderSearch params){
		List<Map<String,Object>> datas = orderReadMapper.list((pageNo - 1) * pageSize, pageSize,params);
		int total = orderReadMapper.listTotal(params);
		return new Pager(datas, total, pageNo, pageSize);
	}
	/**
	 * 修改订单状态
	 * @param id
	 * @param status 2已付款，3已发货，4已收货，5已完成
	 * @return 0失败 1成功
	 */
	@Transactional
	public int modfiyStatus(Long id,Integer status){
		Order order = orderMapper.selectByPrimaryKey(id);
		if(order==null || status==null)return 0;
		order.setOrderStatus(status);
		if(status.intValue()==2){
			order.setPayStatus(2);
		}
		if(status.intValue()==3){
			order.setDeliverStatus(2);
		}
		return orderMapper.updateByPrimaryKey(order);
	}

}
