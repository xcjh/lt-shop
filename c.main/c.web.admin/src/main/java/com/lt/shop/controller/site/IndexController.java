package com.lt.shop.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.utils.GsonUtils;
import com.common.utils.StringUtils;
import com.lt.shop.dao.admin.entity.def.Goods;
import com.lt.shop.service.site.SiteGoodsService;

/**
 * 前台展示页面
 * @author xiaoli
 *
 */
@Controller
@Scope("prototype")
public class IndexController extends SiteController {
	
	@Autowired
	SiteGoodsService siteGoodsService;

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(){
		this.webpage = siteGoodsService.list(1, size);
		request.setAttribute("webpage", webpage);
		return THEME+"/index";
	}
	
	/**
	 * 分页商品列表（异步用）
	 * @return
	 */
	@RequestMapping(value="/lgp",method = RequestMethod.GET,produces = "text/json;charset=UTF-8")
	public String indexPage(){
		String p = request.getParameter("p");
		Integer pageNo = StringUtils.toInt(p);
		pageNo=pageNo==null?1:pageNo;
		this.webpage = siteGoodsService.list(pageNo, size);
		request.setAttribute("webpage", webpage);
		return THEME+"/ajaxlist";
	}
	
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/d/{id}",method=RequestMethod.GET)
	public String detail(@PathVariable Long id){
		Goods goods = siteGoodsService.get(id);
		request.setAttribute("goods", goods);
		String[] arr = goods.getAblums().split(",");
		request.setAttribute("albumsArr", arr);
		request.setAttribute("albumsArrSize", arr.length);
		Object obj = contextService.getObject("site_login");
		if(obj!=null){
			request.setAttribute("islogin", 1);
		}else{
			request.setAttribute("islogin", 0);
		}
		return THEME+"/detail";
	}
}
