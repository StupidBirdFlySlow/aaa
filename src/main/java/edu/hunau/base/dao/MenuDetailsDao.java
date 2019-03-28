package edu.hunau.base.dao;

import java.util.Date;
import java.util.List;

import edu.hunau.base.model.MenuDetails;

public interface MenuDetailsDao {

	public void addMenuDetails(MenuDetails menuDetails);
	
	public MenuDetails getEntityById(String id);
	
	public List<MenuDetails> getEntityList(int pageNum,int pageSize,String type);
	
	public int getTotalRecords(String type);
	
	public List<MenuDetails> getMostHotRecipesOfThisWeek(Date date,int pageSize,int pageNum);
	
	public List<MenuDetails> getMostHotRecipesOfThisMonth(Date date,int pageSize,int pageNum);
	
	public List<MenuDetails> getMostNewRecipes(int pageSize,int pageNum);
	
	public void updateRecipesClickcount(int clickCount,String id);
	
	public List<String> getRecipesNameByKeywords(String keywords);
	
	public List<String> getRecipesIdByKeywords(String keywords);
	
	public void updateMenuDetails(MenuDetails menuDetails);
}
