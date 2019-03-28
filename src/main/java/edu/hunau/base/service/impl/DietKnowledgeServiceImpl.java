package edu.hunau.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hunau.base.dao.DietKnowledgeDao;
import edu.hunau.base.model.DietKnowledge;
import edu.hunau.base.service.DietKnowledgeService;
import edu.hunau.base.support.Page;
import edu.hunau.base.support.PageInfo;

@Service("dietKnowledgeService")
@Transactional
public class DietKnowledgeServiceImpl implements DietKnowledgeService{
	
	@Autowired
	@Qualifier("dietKnowledgeDao")
	private DietKnowledgeDao dietKnowledgeDao;

	public void addDietKnowledge(DietKnowledge entity) {
		dietKnowledgeDao.addDietKnowledge(entity);
	}

	public DietKnowledge getEntityById(String id) {
		return dietKnowledgeDao.getEntityById(id);
	}

	public List<DietKnowledge> getEntityList(int pageSize, int pageNum,String type) {
		return dietKnowledgeDao.getEntityList(pageSize, pageNum,type);
	}

	public PageInfo<DietKnowledge> getPageInfo(int pageSize, int pageNum, String type) {
		List<DietKnowledge> list = dietKnowledgeDao.getEntityList(pageSize, pageNum, type);
		int totalCount = dietKnowledgeDao.getTotalCount(type);
		PageInfo<DietKnowledge> page=new PageInfo<DietKnowledge>(pageNum,pageSize,totalCount,list);
		return page;
	}

}
