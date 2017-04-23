package com.common.valid;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 用户登录入参
 * @author xiaoli
 *
 */
@Data
public class ReqLogin implements Serializable {

	private static final long serialVersionUID = 5575666555557972960L;
	
	@NotEmpty(message = "帐号不能为空")
	private String uname;
	
	@NotEmpty(message = "密码不能为空")
	private String upwd;
	
	private int online;//1为记住登录帐号密码

}
