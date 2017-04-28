package com.lt.shop.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.lt.shop.service.BaseCode;

@Service
public class StartInitListener implements ApplicationListener<ContextRefreshedEvent> {

	protected static final Logger logger = LoggerFactory.getLogger(StartInitListener.class);
	
	@Autowired
	BaseCode saseCode;

	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		//new BaseCode().init();
		if(saseCode!=null){
			logger.info("StartInitListener================is have");
			saseCode.init();
			System.out.println();
		}else{
			logger.info("StartInitListener================is null");
		}
	}

}
