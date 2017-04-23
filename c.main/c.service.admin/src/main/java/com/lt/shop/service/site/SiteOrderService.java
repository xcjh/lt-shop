package com.lt.shop.service.site;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.Constant;
import com.common.service.BaseService;
import com.common.utils.CurrencyUtils;
import com.common.valid.RespCart;
import com.lt.shop.dao.admin.entity.def.Cart;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.dao.admin.impl.def.CartMapper;
import com.lt.shop.dao.site.impl.SiteCartMapper;

/**
 * 购物车业务处理
 * @author xiaoli
 *
 */
@Service
public class SiteOrderService extends BaseService {
	
	@Autowired
	CartMapper cartMapper;
	
	@Autowired
	SiteCartMapper siteCartMapper;
	
	/**
	 * 添加
	 * @param gid
	 * @param num
	 * @return
	 */
	public int add(Long gid,int num){
		User user = (User)contextService.getObject(Constant.SITE_LOGIN_USER);
		Cart cart = siteCartMapper.get(gid, user.getId());
		if(cart!=null){
			cart.setNum(cart.getNum()+num);
			return cartMapper.updateByPrimaryKey(cart);
		}else{
			cart = new Cart();
			cart.setAddTime(System.currentTimeMillis());
			cart.setGoodsId(gid);
			cart.setNum(num);
			cart.setUserId(user.getId());
			return cartMapper.insert(cart);
		}
		
	}
	
	/**
	 * 编辑商品数量
	 * @param gid
	 * @param num
	 * @return
	 */
	public int edit(Long gid,int num){
		User user = (User)contextService.getObject(Constant.SITE_LOGIN_USER);
		Cart cart = siteCartMapper.get(gid, user.getId());
		if(cart!=null){
			cart.setNum(num);
			return cartMapper.updateByPrimaryKey(cart);
		}else{
			cart = new Cart();
			cart.setAddTime(System.currentTimeMillis());
			cart.setGoodsId(gid);
			cart.setNum(num);
			cart.setUserId(user.getId());
			return cartMapper.insert(cart);
		}
		
	}
	
	
	/**
	 * 读取登录用户购物车列表
	 * @return
	 */
	public Map<String,Object> listCart(){
		User user = (User)contextService.getObject(Constant.SITE_LOGIN_USER);
		Map<String,Object> map = new HashMap<String,Object>();
		List<RespCart> list = siteCartMapper.list(user.getId());
		Double amount = 0d;//总额
		Double freightTotal = 0d;//运费总和 
		Double otherWeights=0d;//其他总重量
		for (RespCart respCart : list) {
			double subtotal = CurrencyUtils.mul(respCart.getPrice(), respCart.getNum());
			respCart.setSubtotal(subtotal);
			amount = CurrencyUtils.add(amount, subtotal);
			if(respCart.getFreight().doubleValue()==0d){
				double weightSubtotal = CurrencyUtils.mul(respCart.getWeight(), respCart.getNum());//重量小计
				otherWeights = CurrencyUtils.add(otherWeights, weightSubtotal);
			}else{
				freightTotal = CurrencyUtils.add(freightTotal, respCart.getFreight());
			}
		}
		//计算其他重量运费 
		double otherFreight = countOtherFreight(otherWeights,amount);
		freightTotal = CurrencyUtils.add(freightTotal, otherFreight);
		amount = CurrencyUtils.add(amount, freightTotal);
		map.put("list", list);
		map.put("amount", amount);
		map.put("otherWeights", otherWeights);
		map.put("freightTotal",freightTotal);
		return map;
	}
	
	/**
	 * 计算其他商品费用
	 * @param weights
	 * @param amount
	 * @return
	 */
	public double countOtherFreight(double weights,double amount){
		int Ykg=1;//首重kg
		double YkgFee=7;//首重费用元
		double otherFee=5;//其他每kg费用
		double addWeigh = CurrencyUtils.sub(weights, Ykg);//其余重量
		double addFee = CurrencyUtils.mul(otherFee, Math.floor(Math.abs(addWeigh)));
		return CurrencyUtils.round(CurrencyUtils.add(YkgFee,addFee), 1);
	}

}
