package com.lt.shop.service.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.Constant;
import com.common.service.BaseService;
import com.common.utils.FileUtils;
import com.common.utils.Pager;
import com.common.utils.StringUtils;
import com.common.valid.ReqGoods;
import com.common.valid.ReqGoodsModify;
import com.common.valid.ReqGoodsSearch;
import com.lt.shop.dao.admin.entity.def.Goods;
import com.lt.shop.dao.admin.entity.def.User;
import com.lt.shop.dao.admin.impl.custom.GoodsReadMapper;
import com.lt.shop.dao.admin.impl.custom.GoodsWriteMapper;
import com.lt.shop.dao.admin.impl.def.GoodsMapper;
import com.lt.shop.service.SysInfo;

/**
 * 商品业务处理
 * @author xiaoli
 *
 */
@Service
public class GoodsService extends BaseService {
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	GoodsReadMapper goodsReadMapper;
	
	@Autowired
	GoodsWriteMapper goodsWriteMapper;
	
	/**
	 * 添加
	 */
	@Transactional
	public Long add(ReqGoods req){
		User user = (User)this.contextService.getObject(Constant.ADMIN_LOGIN_USER);
		Goods goods = new Goods();
		goods.setAddTime(System.currentTimeMillis());
		goods.setBuyTotal(0);
		goods.setCid(1l);
		goods.setShopId(1l);
		goods.setUserId(user.getId());
		goods.setStatus(req.getStatus());
		goods.setAblums(StringUtils.arrToStr(req.getAblums(),","));
		goods.setDefImg(req.getAblums()[0]);
		goods.setFreight(new BigDecimal(req.getFreight()));
		goods.setNote(req.getNote());
		goods.setPrice(new BigDecimal(req.getPrice()));
		goods.setPriceCost(new BigDecimal(req.getPriceCost()));
		goods.setPriceMarket(new BigDecimal(req.getPriceMarket()));
		goods.setTitle(req.getTitle());
		goods.setUnit(req.getUnit());
		goods.setWeight(req.getWeight());
		goodsMapper.insert(goods);
		//图片目录处理
		String[] change = new String[req.getAblums().length];
		int i=0;
		for (String path : req.getAblums()) {
			String target = path.replace("temp", ""+goods.getId());
			if(FileUtils.copyFile(SysInfo.IMA_ROOT+path, SysInfo.IMA_ROOT+target)){
				change[i] = target;
			}else{
				logger.debug("图片处理失败->"+path);
				throw new RuntimeException("图片处理失败");
			}
			i++;
		}
		goods.setAblums(StringUtils.arrToStr(change,","));
		goods.setDefImg(change[0]);
		goodsMapper.updateByPrimaryKey(goods);
		//删除临时图片
		String delpath = SysInfo.IMA_ROOT+req.getAblums()[0].split("temp/")[0]+"temp/";
		FileUtils.delete(delpath);
		return goods.getId();
	}
	
	/**
	 * 获取商品实体
	 * @param id
	 * @return
	 */
	public Goods get(Long id){
		return goodsMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 商品修改保存
	 */
	@Transactional
	public int update(ReqGoodsModify req){
		Goods goods = goodsMapper.selectByPrimaryKey(req.getId());
		goods.setStatus(req.getStatus());
		if(req.getAblums()!=null){
			//图片目录处理
			String[] change = new String[req.getAblums().length];
			int i=0;
			for (String path : req.getAblums()) {
				String target = path.replace("temp", ""+goods.getId());
				if(FileUtils.copyFile(SysInfo.IMA_ROOT+path, SysInfo.IMA_ROOT+target)){
					change[i] = target;
				}else{
					logger.debug("图片处理失败->"+path);
					throw new RuntimeException("图片处理失败");
				}
				i++;
			}
			//删除临时图片
			String delpath = SysInfo.IMA_ROOT+req.getAblums()[0].split("temp/")[0]+"temp/";
			FileUtils.delete(delpath);
			goods.setAblums(goods.getAblums()+","+StringUtils.arrToStr(change,","));
			goods.setDefImg(req.getAblums()[0]);
		}
		goods.setFreight(new BigDecimal(req.getFreight()));
		goods.setNote(req.getNote());
		goods.setPrice(new BigDecimal(req.getPrice()));
		goods.setPriceCost(new BigDecimal(req.getPriceCost()));
		goods.setPriceMarket(new BigDecimal(req.getPriceMarket()));
		goods.setTitle(req.getTitle());
		goods.setUnit(req.getUnit());
		goods.setWeight(req.getWeight());
		return goodsMapper.updateByPrimaryKey(goods);
	}
	
	/**
	 * 商品分页列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager list(int pageNo, int pageSize,ReqGoodsSearch params){
		List<Map<String,Object>> datas = goodsReadMapper.list((pageNo - 1) * pageSize, pageSize,1l,params);
		int total = goodsReadMapper.listTotal(1l,params);
		return new Pager(datas, total, pageNo, pageSize);
	}
	
	/**
	 * 修改商品状态
	 * @param id 商品id
	 * @param status 新状态
	 * @return
	 */
	public int modfiyStatus(Long id,Integer status){
		return goodsWriteMapper.modfiyStatus(id, status);
	}
	
	/**
	 * 删除图片
	 * @param id
	 * @param imgs
	 */
	@Transactional
	public int delImgs(Long id,String[] imgs){
		Goods goods = goodsMapper.selectByPrimaryKey(id);
		String ablums = ","+goods.getAblums()+",";
		for (String string : imgs) {
			ablums=ablums.replaceAll(","+string+",", ",");
		}
		if(StringUtils.isEmpty(ablums) || ",".equals(ablums)){
			return 2;//不能全部删除图片
		}
		ablums = ablums.substring(1, ablums.length()-1);
		goods.setAblums(ablums);
		String[] arr = ablums.split(",");
		goods.setDefImg(arr[0]);
		int result =  goodsMapper.updateByPrimaryKey(goods);
		if(result==1){
			//删除图片
			for (String string : imgs) {
				FileUtils.deleteFile(SysInfo.IMA_ROOT+string);
			}
		}
		return result;
	}

}
