package edu.hunau.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hunau.base.dao.MenuDetailsDao;
import edu.hunau.base.model.MenuDetails;
import edu.hunau.base.service.MenuDetailsService;
import edu.hunau.base.support.PageInfo;

@Service("menuDetailsService")
@Transactional
public class MenuDetailsServiceImpl implements MenuDetailsService {

	@Autowired
	@Qualifier("menuDetailsDao")
	private MenuDetailsDao menuDetailsDao;

	public void addMenuDetails(MenuDetails menuDetails) {
		menuDetailsDao.addMenuDetails(menuDetails);
	}

	public MenuDetails getEntityById(String id) {
		return menuDetailsDao.getEntityById(id);
	}

	public List<MenuDetails> getEntityList(int pageNum, int pageSize,String type) {
		return menuDetailsDao.getEntityList(pageNum, pageSize,type);
	}

	public PageInfo<MenuDetails> getEntityPage(int pageNum, int pageSize, String type) {
		
		List<MenuDetails> entityList = menuDetailsDao.getEntityList(pageNum, pageSize,type);
		int totalRecords = menuDetailsDao.getTotalRecords(type);
		PageInfo<MenuDetails> page = new PageInfo<MenuDetails>(pageNum,pageSize,totalRecords,entityList);
		return page;
	}

	public List<MenuDetails> getMostHotRecipesOfThisWeek(Date date, int pageSize, int pageNum) {
		return menuDetailsDao.getMostHotRecipesOfThisWeek(date, pageSize, pageNum);
	}

	public List<MenuDetails> getMostHotRecipesOfThisMonth(Date date, int pageSize, int pageNum) {
		return menuDetailsDao.getMostHotRecipesOfThisMonth(date, pageSize, pageNum);
	}

	public List<MenuDetails> getMostNewRecipes(int pageSize, int pageNum) {
		return menuDetailsDao.getMostNewRecipes(pageSize, pageNum);
	}

	/**
	 * 更新菜谱的浏览数量
	 */
	public void updateRecipesClickcountById(String id) {
		MenuDetails entity = menuDetailsDao.getEntityById(id);
		menuDetailsDao.updateRecipesClickcount(entity.getClickCount()+1,entity.getId());
	}

	public List<String> getRecipesNameByKeywords(String keywords) {
		return menuDetailsDao.getRecipesNameByKeywords(keywords);
	}

	public List<String> getRecipesIdByKeywords(String keywords) {
		return menuDetailsDao.getRecipesIdByKeywords(keywords);
	}

	public int getTotalRecords(String type) {
		return menuDetailsDao.getTotalRecords(type);
	}

	public void updateMenuDetails(MenuDetails menuDetails) {
		menuDetailsDao.updateMenuDetails(menuDetails);
	}

}
