package com.common.valid;

import java.io.Serializable;

import lombok.Data;

/**
 * 购物车显示用
 * @author Edwin
 *
 */
@Data
public class RespCart implements Serializable{

	private static final long serialVersionUID = 7355782837772428999L;
	
	private Long cartId;//购物车ID
	
	private Long goodsId;//商品ID
	
	private int num;//购买数量
	
	private String title;//商品名称
	
	private Double price;//商品价格
	
	private Double freight;//商品运费
	
	private Double weight;//商品重量
	
	private String defImg;//默认图
	
	private Double subtotal;//小计
	

}
