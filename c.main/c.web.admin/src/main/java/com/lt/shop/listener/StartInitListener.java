package com.lt.shop.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.lt.shop.service.BaseCode;

@Service
public class StartInitListener implements ApplicationListener<ContextRefreshedEvent> {

	protected static final Logger logger = LoggerFactory.getLogger(StartInitListener.class);

	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		new BaseCode().init();
	}

}
