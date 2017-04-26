package com.common.valid;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 配置修改参数
 * @author Edwin
 *
 */
@Data
public class ReqConfigModify implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6823317112080445193L;
	
	@NotNull(message="ID不能为空")
	private Long id;

	@NotEmpty(message="邮箱不能为空")
    private String email;

	@NotEmpty(message="手机不能为空")
    private String phone;

	@NotEmpty(message="地址不能为空")
    private String address;

	@NotEmpty(message="微博不能为空")
    private String weibo;

	@NotEmpty(message="微信不能为空")
    private String weixin;

	@NotEmpty(message="协议不能为空")
    private String xieyi;
	

}
