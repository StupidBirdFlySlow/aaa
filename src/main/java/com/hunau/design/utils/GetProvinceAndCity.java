package com.hunau.design.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.hunau.base.model.ProvinceAndCity;
import edu.hunau.base.service.ProvinceAndCityService;
import edu.hunau.base.support.ProvinceAndCityDatas;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class GetProvinceAndCity {

	@Autowired
	@Qualifier("provinceAndCityService")
	private ProvinceAndCityService provinceAndCityService;
	
	@Test
	public void getDatas(){
		ProvinceAndCityDatas[] values = ProvinceAndCityDatas.values();
		for(ProvinceAndCityDatas value:values){
			ProvinceAndCity entity=new ProvinceAndCity();
			entity.setCity(value.getCity());
			entity.setProvince(value.getProvince());
			entity.setProvince_short(value.getProvince_short());
			provinceAndCityService.addRecords(entity);
		}
	}
}
