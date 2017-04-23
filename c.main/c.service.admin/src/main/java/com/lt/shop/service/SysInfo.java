package com.lt.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SysInfo {
	protected static final Logger logger = LoggerFactory.getLogger(SysInfo.class);
	public static String IMG_DOMAIN;//图片域名
	public static String IMA_ROOT;//图片根目录
	
	
	@Value("${path.img.root}")
	public void setImgRoot(String val){
		SysInfo.IMA_ROOT = val;
	}
	
	@Value("${path.img.domain}")
	public void setImgDomain(String val){
		SysInfo.IMG_DOMAIN = val;
	}
	
}
