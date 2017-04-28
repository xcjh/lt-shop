package com.lt.shop.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.shop.dao.admin.entity.def.CodeCity;
import com.lt.shop.dao.admin.entity.def.CodeDistrict;
import com.lt.shop.dao.admin.entity.def.CodeProvince;
import com.lt.shop.dao.admin.impl.custom.CodeReadCityMapper;
import com.lt.shop.dao.admin.impl.custom.CodeReadDistrictMapper;
import com.lt.shop.dao.admin.impl.custom.CodeReadProvinceMapper;

@Service
public class BaseCode {

	@Autowired
	CodeReadProvinceMapper provinceMapper;

	@Autowired
	CodeReadCityMapper cityMapper;

	@Autowired
	CodeReadDistrictMapper districtMapper;

	public void init() {
		System.out.println("this is base code");
		List<CodeProvince> lstProvince = provinceMapper.list();
		List<CodeCity> lstCity = cityMapper.list();
		List<CodeDistrict> lstDistrict = districtMapper.list();
		if (lstProvince != null && !lstProvince.isEmpty()) {
			lstProvince.stream().forEach(item -> {
				BaseCode.provinceMap.put(item.getProvinceid().toString(), item);
			});
		}
		if (lstCity != null && !lstCity.isEmpty()) {
			lstCity.stream().forEach(item -> {
				BaseCode.cityMap.put(item.getCityid().toString(), item);
			});
		}
		if (lstDistrict != null && !lstDistrict.isEmpty()) {
			lstDistrict.stream().forEach(item -> {
				BaseCode.districtMap.put(item.getDistrictid().toString(), item);
			});
		}
	}

	public static CodeProvince getProvince(String provinceId) {
		return provinceMap.get(provinceId);
	}

	public static List<CodeProvince> getProvinces() {
		return provinceMap.values().stream().collect(Collectors.toList());
	}

	public static CodeCity getCity(String cityId) {
		return cityMap.get(cityId);
	}

	public static List<CodeCity> getCitys(String provinceId) {
		return cityMap.values().stream().filter(item -> item.getProvinceid().equals(provinceId))
				.collect(Collectors.toList());
	}

	public static CodeDistrict getDistrict(String districtId) {
		return districtMap.get(districtId);
	}

	public static List<CodeDistrict> getDistricts(String cityId) {
		return districtMap.values().stream().filter(item -> item.getCityid().equals(cityId))
				.collect(Collectors.toList());
	}

	protected static Map<String, CodeProvince> provinceMap = new ConcurrentHashMap<>();
	protected static Map<String, CodeCity> cityMap = new ConcurrentHashMap<>();
	protected static Map<String, CodeDistrict> districtMap = new ConcurrentHashMap<>();
}
