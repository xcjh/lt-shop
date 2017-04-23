package com.lt.shop.controller.admin;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class AdminController extends BaseController {
	
	/**
	 * 主题包名
	 */
	protected String THEME = "admin";

}
