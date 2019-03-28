package edu.hunau.base.dao;

import java.util.List;

import edu.hunau.base.model.ShiCaiBaiKe;
import edu.hunau.base.support.PageInfo;

public interface ShiCaiBaiKeDao {

	public void addShiCaiBaiKe(ShiCaiBaiKe entity);
	
	public ShiCaiBaiKe getEntityById(String id);
	
	public List<ShiCaiBaiKe> getEntityList(int pageNum,int pageSize,String type);
	
	public int getTotalCount(String type);
	
	
	
}
