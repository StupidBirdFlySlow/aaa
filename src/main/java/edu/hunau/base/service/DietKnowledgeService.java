package edu.hunau.base.service;

import java.util.List;

import edu.hunau.base.model.DietKnowledge;
import edu.hunau.base.support.PageInfo;

public interface DietKnowledgeService {

	public void addDietKnowledge(DietKnowledge entity);
	
	public DietKnowledge getEntityById(String id);
	
	public List<DietKnowledge> getEntityList(int pageSize,int pageNum,String type);
	
	public PageInfo<DietKnowledge> getPageInfo(int pageSize,int pageNum,String type);
}
