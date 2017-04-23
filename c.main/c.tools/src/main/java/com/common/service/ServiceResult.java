package com.common.service;

import lombok.Data;

/**
 * 业务处理返回实体
 * @author xiaoli
 *
 * @param <T>
 */
@Data
public class ServiceResult<T> {
	private int result;
	private T t;

}
