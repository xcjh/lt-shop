package com.lt.shop.service.site;

import java.util.Date;

import javax.swing.Box.Filler;

import org.netbeans.lib.cvsclient.commandLine.command.add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.service.BaseService;
import com.lt.shop.dao.admin.entity.custom.UserAddrEntity;
import com.lt.shop.dao.site.impl.SiteUserAddrMapper;

/**
 * 用户地址管理<br>
 * 暂时不实现用户地址管理，每次将用户录入的地址更新到用户已有的地址即可
 * 
 * @author do
 *
 */
@Service
public class SiteUserAddrService extends BaseService {

	@Autowired
	SiteUserAddrMapper siteUserAddrMapper;

	public UserAddrEntity getDefault(Long userId) {
		UserAddrEntity addr = siteUserAddrMapper.selectByUser(userId);
		if (addr == null) {
			addr = new UserAddrEntity();
		}
		/*
		 * addr.setReceiptName("萧离"); addr.setReceiptPhone("110000000000");
		 * addr.setProvinceId(23l); addr.setCityId(235l);
		 * addr.setDistrictId(2041l); addr.setAddress("安平街20号");
		 */
		return addr;
	}

	private void fill(UserAddrEntity addr, Long userId, String receiptName, String receiptPhone, Long provinceId,
			Long cityId, Long districtId, String address) {
		addr.setUserId(userId);
		addr.setReceiptName(receiptName);
		addr.setReceiptPhone(receiptPhone);
		addr.setProvinceId(provinceId);
		addr.setCityId(cityId);
		addr.setDistrictId(districtId);
		addr.setAddress(address);
		// addr.setProvince();
		// addr.setCity();
		// addr.setDistrict();
		addr.setDefaultUsed(1);
	}

	public UserAddrEntity get(Long userId, String receiptName, String receiptPhone, Long provinceId, Long cityId,
			Long districtId, String address) {
		UserAddrEntity addr = siteUserAddrMapper.selectByUser(userId);
		if (addr == null) {
			addr = new UserAddrEntity();
			addr.setAddTime(new Date());
			fill(addr, userId, receiptName, receiptPhone, provinceId, cityId, districtId, address);
			siteUserAddrMapper.insert(addr);
		} else {
			fill(addr, userId, receiptName, receiptPhone, provinceId, cityId, districtId, address);
			siteUserAddrMapper.updateByPrimaryKey(addr);
		}
		return addr;
	}
}
