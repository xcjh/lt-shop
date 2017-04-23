package com.lt.shop.controller.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.controller.BaseController;

@Controller
@Scope("prototype")
public class TestController extends BaseController {
	
	/**
	 * 测试首页
	 * @return
	 */
	@RequestMapping(value="/test/index",method=RequestMethod.GET)
	public String index(){
		return "test/index";
	}

}
