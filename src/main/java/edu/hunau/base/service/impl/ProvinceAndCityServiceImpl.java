package edu.hunau.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hunau.base.dao.ProvinceAndCityDao;
import edu.hunau.base.model.ProvinceAndCity;
import edu.hunau.base.service.ProvinceAndCityService;

@Service("provinceAndCityService")
@Transactional
public class ProvinceAndCityServiceImpl implements ProvinceAndCityService{

	@Autowired
	@Qualifier("provinceAndCityDao")
	private ProvinceAndCityDao provinceAndCityDao;
	
	public void addRecords(ProvinceAndCity entity) {
		provinceAndCityDao.addRecords(entity);
	}

	public List<String> getProvinceList() {
		return provinceAndCityDao.getProvinceList();
	}

	public List<String> getCityListByProvince(String province) {
		return provinceAndCityDao.getCityListByProvince(province);
	}

}
