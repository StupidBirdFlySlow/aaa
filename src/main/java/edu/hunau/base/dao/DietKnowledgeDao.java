package edu.hunau.base.dao;

import java.util.List;

import edu.hunau.base.model.DietKnowledge;

public interface DietKnowledgeDao {

	public void addDietKnowledge(DietKnowledge entity);
	
	public DietKnowledge  getEntityById(String id);
	
	public List<DietKnowledge> getEntityList(int pageSize,int pageNum,String type);
	
	public int getTotalCount(String type);
	
}
