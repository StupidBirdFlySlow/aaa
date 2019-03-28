package edu.hunau.base.service;

import java.util.List;

import edu.hunau.base.model.ProvinceAndCity;

public interface ProvinceAndCityService {

	public void addRecords(ProvinceAndCity entity);

	public List<String> getProvinceList();

	public List<String> getCityListByProvince(String province);
}
