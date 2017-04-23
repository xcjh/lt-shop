package com.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T> {
	
	protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
	@Autowired
	protected ContextService<T> contextService;
	protected ServiceResult<T> sResult = new ServiceResult<T>();
}
