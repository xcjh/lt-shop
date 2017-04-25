package com.lt.shop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utils.StringUtils;
import com.common.valid.ReqOrderSearch;
import com.lt.shop.service.admin.OrderService;

/**
 * 订单管理
 * @author xiaoli
 *
 */
@Controller
@Scope("prototype")
public class AdminOrderController extends AdminController {
	
	@Autowired
	OrderService orderService;
	
	/**
	 * 订单列表/搜索
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/o/l{page}",method=RequestMethod.GET)
	public String list(@PathVariable String page,ReqOrderSearch params){
		//获取页码
		Integer pageNo = StringUtils.toInt(page);
		pageNo=pageNo==null?1:pageNo;
		pageNo=pageNo<1?1:pageNo;
		this.webpage = orderService.list(pageNo, pageSize,params);
		loadPagerHtml();
		request.setAttribute("pager", webpage);
		request.setAttribute("pageNo",pageNo);
		request.setAttribute("params",params);
		return THEME+"/order/list";
	}
	
	/**
	 * 修改订单状态，付款，发货，收货
	 * @param id 用户ID
	 * @param status 新状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/o/m/{id}/{status}",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String modfiyStatus(@PathVariable Long id,@PathVariable Integer status){
		if(id==null){
			return resp(2,"ID不能为空");
		}
		if(status==null){
			return resp(2,"新状态不能为空");
		}
		return resp(orderService.modfiyStatus(id, status));
	}

}
