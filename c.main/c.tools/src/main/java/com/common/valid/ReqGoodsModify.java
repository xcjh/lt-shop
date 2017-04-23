package com.common.valid;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 添加商品参数
 * @author xiaoli
 *
 */
@Data
public class ReqGoodsModify implements Serializable {
	
	private static final long serialVersionUID = -3612660640481734006L;
	
	@NotNull(message="商品ID不能为空")
	private Long id;

	@NotEmpty(message="标题不能为空")
	private String title;
	
	@NotNull(message="商品不能为空")
	private Double freight;

	@NotNull(message="市场价不能为空")
    private Double priceMarket;

	@NotNull(message="销售价不能为空")
    private Double price;

	@NotNull(message="成本价不能为空")
    private Double priceCost;

	@NotEmpty(message="计量单位不能为空")
    private String unit;

	@NotNull(message="重量不能为空")
    private Double weight;
    
//	@NotNull(message="相册不能为空")
    private String[] ablums;
    
	@NotEmpty(message="详细说明不能为空")
    private String note;
	
	private int status;//商品状态
	
	

}
