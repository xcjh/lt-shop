package com.common.valid;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 订单搜索条件
 * @author xiaoli
 *
 */
@Data
public class ReqOrderSearch implements Serializable {
	
	private static final long serialVersionUID = -4938841252569672532L;
	private String w;//关键字
	
	
	
	

}
