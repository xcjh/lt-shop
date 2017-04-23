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
	
	public int doEndTag() throws JspException {
		if(price==null)this.print("");
		price = CurrencyUtils.round(price, 1);
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

}
