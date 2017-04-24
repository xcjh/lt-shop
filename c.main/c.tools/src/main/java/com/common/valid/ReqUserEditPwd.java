package com.common.valid;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class ReqUserEditPwd implements Serializable {
	
	private static final long serialVersionUID = -1621296254808478993L;
	

	@NotNull(message = "唯一标识不能为空")
	private Long id; 

	@NotEmpty(message = "登录密码不能为空")
    private String upwd;//密码

	@NotEmpty(message = "确认密码不能为空")
    private String upwd2;//密码
}
