package edu.hunau.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hunau.base.dao.ShiCaiBaiKeDao;
import edu.hunau.base.model.MenuDetails;
import edu.hunau.base.model.ShiCaiBaiKe;
import edu.hunau.base.service.MaterialService;
import edu.hunau.base.service.MenuDetailsService;
import edu.hunau.base.service.ShiCaiBaiKeService;
import edu.hunau.base.support.PageInfo;

@Service("shiCaiBaiKeService")
@Transactional
public class ShiCaiBaiKeServiceImpl implements ShiCaiBaiKeService {

	@Autowired
	@Qualifier("shiCaiBaiKeDao")
	private ShiCaiBaiKeDao shiCaiBaiKeDao;
	
	@Autowired
	@Qualifier("menuDetailsService")
	private MenuDetailsService menuDetailsService;
	
	@Autowired
	@Qualifier("meterialService")
	private MaterialService materialService;

	public void addShiCaiBaiKe(ShiCaiBaiKe entity) {
		shiCaiBaiKeDao.addShiCaiBaiKe(entity);
	}

	public ShiCaiBaiKe getEntity(String id) {
		return shiCaiBaiKeDao.getEntityById(id);
	}

	public List<ShiCaiBaiKe> getEntityList(int pageSize, int pageNum,String type) {
		return shiCaiBaiKeDao.getEntityList(pageNum, pageSize,type);
	}

	public List<MenuDetails> getMenuDetailsByReferMaterial(String clmc, int pageSize, int pageNum) {
		List<String> cpIds = materialService.getCpIdByName(clmc, pageSize, pageNum);
		List<MenuDetails> result=new ArrayList<MenuDetails>();
		for(String cpId:cpIds){
			MenuDetails menuDetials = menuDetailsService.getEntityById(cpId);
			result.add(menuDetials);
		}
		return result;
	}

	public PageInfo<ShiCaiBaiKe> getPageInfo(int pageSize, int pageNum, String type) {
		PageInfo<ShiCaiBaiKe> page=new PageInfo<ShiCaiBaiKe>();
		List<ShiCaiBaiKe> list = shiCaiBaiKeDao.getEntityList(pageNum, pageSize, type);
		int totalCount = shiCaiBaiKeDao.getTotalCount(type);
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		page.setTotalRecords(totalCount);
		page.setList(list);
		return page;
	}
	
	

}
