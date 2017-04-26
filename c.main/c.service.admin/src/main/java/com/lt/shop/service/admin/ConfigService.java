package com.lt.shop.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.valid.ReqConfigModify;
import com.lt.shop.dao.admin.entity.def.Config;
import com.lt.shop.dao.admin.impl.def.ConfigMapper;

/**
 * 配置业务处理
 * @author xiaoli
 *
 */
@Service
public class ConfigService {
	
	@Autowired
	ConfigMapper configMapper;
	
	/**
	 * 获取配置对象
	 * @return
	 */
	public Config get(Long id){
		return configMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 更新配置
	 * @param config
	 * @return
	 */
	public int update(ReqConfigModify config){
		Config c = configMapper.selectByPrimaryKey(config.getId());
		c.setAddress(config.getAddress());
		c.setEmail(config.getEmail());
		c.setPhone(config.getPhone());
		c.setWeibo(config.getWeibo());
		c.setWeixin(config.getWeixin());
		c.setXieyi(config.getXieyi());
		return configMapper.updateByPrimaryKey(c);
	}

}
