package com.lt.shop.controller.site.vail;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class VailLogin {
	
	@NotEmpty(message="手机号不能为空")
	public String uname;
	
	@NotEmpty(message="密码不能为空")
	public String upwd;

}
