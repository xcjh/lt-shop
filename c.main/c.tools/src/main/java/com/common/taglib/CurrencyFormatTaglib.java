package com.common.taglib;

import javax.servlet.jsp.JspException;

import com.common.utils.CurrencyUtils;

/**
 * 
* @ClassName: CurrencyFormatTaglib 
* @Description: 货币格式 
* @author xiaoli
* @date 2016年12月28日 上午11:23:38 
*
 */
public class CurrencyFormatTaglib extends TagSupport  {
	
	
	private Double price;
	
	private Double sub;//减去金额
	
	private Double add;//增加金额
	
	public int doEndTag() throws JspException {
		if(price==null)this.print("");
		sub=sub==null?0d:sub;
		add=add==null?0d:add;
		price = CurrencyUtils.add(price, add);
		price = CurrencyUtils.sub(price, sub);
		price = CurrencyUtils.round(price);
		this.print("&yen;"+CurrencyUtils.doubleTrans(price));
		return this.EVAL_BODY_INCLUDE;
	}

	
	public int doStartTag() throws JspException {
		return this.EVAL_PAGE;
	}
	

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	public Double getSub() {
		return sub;
	}


	public void setSub(Double sub) {
		this.sub = sub;
	}


	public Double getAdd() {
		return add;
	}


	public void setAdd(Double add) {
		this.add = add;
	}
	
	

}
