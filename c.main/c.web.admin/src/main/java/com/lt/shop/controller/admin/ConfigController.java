package com.lt.shop.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.common.valid.ReqConfigModify;
import com.common.valid.ReqGoodsModify;
import com.lt.shop.dao.admin.entity.def.Goods;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.service.admin.ConfigService;

/**
 * 配置设置
 * @author xiaoli
 *
 */
@Controller
@Scope("prototype")
public class ConfigController extends AdminController {
	
	@Autowired
	ConfigService configService;
	
	/**
	 * 配置编辑
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/c/c-{id}",method=RequestMethod.GET)
	public String edit(@PathVariable Long id){
		request.setAttribute("config", configService.get(id));
		return THEME+"/config/edit"; 
	}
	
	/**
	 * 配置编辑-保存
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/c/m",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String modify(@Valid ReqConfigModify req,BindingResult result){
		if(result.hasErrors()){
			return resp(2,getMsg(result));
		}
		int id = configService.update(req);
		if(id!=1){
			return resp(2,"编辑配置失败");
		}
		return resp(1);
	}

}
