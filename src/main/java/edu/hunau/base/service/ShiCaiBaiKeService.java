package edu.hunau.base.service;

import java.util.List;

import edu.hunau.base.model.MenuDetails;
import edu.hunau.base.model.ShiCaiBaiKe;
import edu.hunau.base.support.PageInfo;

public interface ShiCaiBaiKeService {

	public void addShiCaiBaiKe(ShiCaiBaiKe entity);
	
	public ShiCaiBaiKe getEntity(String id);
	
	public List<ShiCaiBaiKe> getEntityList(int pageSize , int pageNum,String type);
	
	public List<MenuDetails> getMenuDetailsByReferMaterial(String clmc,int pageSize,int pageNum);
	
	public PageInfo<ShiCaiBaiKe> getPageInfo(int pageSize,int pageNum,String type);
}
