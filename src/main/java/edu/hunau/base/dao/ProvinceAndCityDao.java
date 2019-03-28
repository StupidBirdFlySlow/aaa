package edu.hunau.base.dao;

import java.util.List;

import edu.hunau.base.model.ProvinceAndCity;

public interface ProvinceAndCityDao {

	public void addRecords(ProvinceAndCity entity);
	
	public List<String> getProvinceList();
	
	public List<String> getCityListByProvince(String province);
	
}
