package com.common.taglib;

import javax.servlet.jsp.JspException;

import lombok.Data;

/**
 * 订单中各种状态状态显示表情
 * @author Edwin
 *
 */
@Data
public class OrderStatusTaglib extends TagSupport {
	
	private String filed;//字段
	private int val;//字典值
	
	public int doEndTag() throws JspException {
		String txt = "";
		if(filed != null){
			if("order".equals(filed)){
				//订单状态 0已取消,1新订单,2已发货,3已发货,4已收货,5已完成
				if(val==0){
					txt="已取消";
				}
				else if(val==1){
					txt="新订单";
				}
				else if(val==2){
					txt="已发货";
				}
				else if(val==3){
					txt="已发货";
				}
				else if(val==4){
					txt="已收货";
				}
				else if(val==5){
					txt="已完成";
				}
			}
			else if("pay".equals(filed)){
				//支付状态 1未付款，2已付款，3已退款
				if(val==1){
					txt="未付款";
				}
				else if(val==2){
					txt="已付款";
				}
				else if(val==3){
					txt="已退款";
				}
			}
			else if("deliver".equals(filed)){
				//发货状态 1未发货，2已发货，3已退货
				if(val==1){
					txt="未发货";
				}
				else if(val==2){
					txt="已发货";
				}
				else if(val==3){
					txt="已退货";
				}
			}
		}
		this.print(txt);
		return this.EVAL_BODY_INCLUDE;
	}

	
	public int doStartTag() throws JspException {
		return this.EVAL_PAGE;
	}
	
	

}
