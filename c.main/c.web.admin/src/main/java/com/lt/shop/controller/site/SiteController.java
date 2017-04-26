package com.lt.shop.controller.site;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.controller.BaseController;

@Controller
@Scope("prototype")
public class SiteController extends BaseController {
	
	/**
	 * 主题包名
	 */
	protected String THEME = "site";
	
	protected int size = 10;

}
