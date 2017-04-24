package com.common.valid;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 用户搜索条件
 * @author xiaoli
 *
 */
@Data
public class ReqUserSearch implements Serializable {
	
	private static final long serialVersionUID = -5006293874511965071L;
	private String w;//关键字
	
	
	
	

}
