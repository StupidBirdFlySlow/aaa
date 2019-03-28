package edu.hunau.base.service;

import java.util.Date;
import java.util.List;

import edu.hunau.base.model.MenuDetails;
import edu.hunau.base.support.PageInfo;

public interface MenuDetailsService {

	public void addMenuDetails(MenuDetails menuDetails);
	
	public MenuDetails getEntityById(String id);
	
	public List<MenuDetails> getEntityList(int pageNum,int pageSize,String type);
	
	public PageInfo<MenuDetails> getEntityPage(int pageNum,int pageSize,String type);
	
	public List<MenuDetails> getMostHotRecipesOfThisWeek(Date date,int pageSize,int pageNum);
	
	public List<MenuDetails> getMostHotRecipesOfThisMonth(Date date,int pageSize ,int pageNum);
	
	public List<MenuDetails> getMostNewRecipes(int pageSize,int pageNum);
	
	public void updateRecipesClickcountById(String id);
	
	public List<String> getRecipesNameByKeywords(String keywords);
	
	public List<String> getRecipesIdByKeywords(String keywords);
	
	public int getTotalRecords(String type);
	
	public void updateMenuDetails(MenuDetails menuDetails);
	
}
