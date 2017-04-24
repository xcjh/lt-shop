package com.lt.shop.dao.admin.impl.custom;

import org.apache.ibatis.annotations.Param;

public interface UserWriteMapper {
	
	/**
	 * 修改用户状态
	 * @param id
	 * @param status
	 * @return
	 */
	public int modfiyStatus(@Param("id")Long id,@Param("status")Integer status);

}
