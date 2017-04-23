package com.lt.shop.service.site;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.service.BaseService;
import com.common.utils.Pager;
import com.lt.shop.dao.admin.entity.def.Goods;
import com.lt.shop.dao.admin.impl.def.GoodsMapper;
import com.lt.shop.dao.site.impl.SiteGoodsMapper;

/**
 * 商品业务处理
 * @author xiaoli
 *
 */
@Service
public class SiteGoodsService extends BaseService {
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	SiteGoodsMapper siteGoodsMapper;
	
	
	
	/**
	 * 获取商品实体
	 * @param id
	 * @return
	 */
	public Goods get(Long id){
		return goodsMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 商品分页列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager list(int pageNo, int pageSize){
		List<Map<String,Object>> datas = siteGoodsMapper.list((pageNo - 1) * pageSize, pageSize,1l);
		int total = siteGoodsMapper.listTotal(1l);
		return new Pager(datas, total, pageNo, pageSize);
	}
	

}
