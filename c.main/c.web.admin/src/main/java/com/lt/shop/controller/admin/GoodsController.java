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
import com.common.utils.StringUtils;
import com.common.valid.ReqGoods;
import com.common.valid.ReqGoodsModify;
import com.common.valid.ReqGoodsSearch;
import com.lt.shop.dao.admin.entity.def.Goods;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.service.admin.GoodsService;

/**
 * 商品管理
 * @author xiaoli
 *
 */
@Controller
@Scope("prototype")
public class GoodsController extends AdminController {
	
	@Autowired
	GoodsService goodsService;
	
	/**
	 * 商品列表/搜索
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/g/l{page}",method=RequestMethod.GET)
	public String list(@PathVariable String page,ReqGoodsSearch params){
		//获取页码
		Integer pageNo = StringUtils.toInt(page);
		pageNo=pageNo==null?1:pageNo;
		pageNo=pageNo<1?1:pageNo;
		this.webpage = goodsService.list(pageNo, pageSize,params);
		loadPagerHtml();
		request.setAttribute("pager", webpage);
		request.setAttribute("pageNo",pageNo);
		request.setAttribute("params",params);
		return THEME+"/goods/list";
	}
	
	/**
	 * 商品增加
	 * @return
	 */
	@RequestMapping(value="/g/a",method=RequestMethod.GET)
	public String add(){
		User user = (User)this.contextService.getObject(Constant.ADMIN_LOGIN_USER);
		request.setAttribute("uid", user.getId());
		return THEME+"/goods/add";
	}
	
	/**
	 * 商品保存
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/g/s",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String save(@Valid ReqGoods req,BindingResult result){
		if(result.hasErrors()){
			return resp(2,getMsg(result));
		}
		Long id = goodsService.add(req);
		if(id==null){
			return resp(2,"保存商品失败");
		}
		return resp(1);
	}
	
	/**
	 * 商品编辑
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/g/e-{id}",method=RequestMethod.GET)
	public String edit(@PathVariable Long id){
		Goods goods = goodsService.get(id);
		request.setAttribute("goods", goods);
		request.setAttribute("uid", 1);
		return THEME+"/goods/edit"; 
	}
	
	/**
	 * 商品编辑-保存
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/g/m",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String modify(@Valid ReqGoodsModify req,BindingResult result){
		if(result.hasErrors()){
			return resp(2,getMsg(result));
		}
		int id = goodsService.update(req);
		if(id!=1){
			return resp(2,"编辑商品失败");
		}
		return resp(1);
	}
	
	/**
	 * 修改商品状态
	 * @param id 商品ID
	 * @param status 新状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/g/m/{id}/{status}",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String modfiyStatus(@PathVariable Long id,@PathVariable Integer status){
		if(id==null){
			return resp(2,"商品ID不能为空");
		}
		if(status==null){
			return resp(2,"新状态不能为空");
		}else if(status<0 || status>3){
			return resp(2,"新状态不正确");
		}
		return resp(goodsService.modfiyStatus(id, status));
	}
	/**
	 * 相册管理
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/g/sa-{id}",method=RequestMethod.GET)
	public String showAblums(@PathVariable Long id){
		Goods goods = goodsService.get(id);
		request.setAttribute("goods", goods);
		String[] ablums = goods.getAblums().split(",");
		request.setAttribute("ablums", ablums);
		request.setAttribute("size", ablums.length);
		return THEME+"/goods/show_ablums"; 
	} 
	
	/**
	 * 删除图片
	 * @param id 商品id
	 * @param imgs 要删除的图片数组
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/g/dgi-{id}",method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String delGoodsImg(@PathVariable Long id,String imgs){
		if(id==null){
			return resp(2,"商品ID不能为空");
		}
		if(imgs==null){
			return resp(2,"要删除的图片不能为空");
		}
		int flag = goodsService.delImgs(id, imgs.split(","));
		if(flag==1){
			return resp(1);
		}else if(flag==2){
			return resp(2,"删除失败,相册不能为空");
		}
		return resp(2,"删除图片失败");
	}

}
