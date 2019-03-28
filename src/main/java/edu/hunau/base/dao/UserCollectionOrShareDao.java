package edu.hunau.base.dao;

import java.util.List;

import edu.hunau.base.model.UserCollectionOrShare;

public interface UserCollectionOrShareDao {

	public void addRecords(UserCollectionOrShare entity);
	
	public List<UserCollectionOrShare> getEntityListByUserId(String userId);
	
	public int getTotalCountOfUsers(String userId,int type);
	
	public int getCollectionCountByCpId(String cpId);
	
	public List<String> getCollectionCpIdsByUserId(String userId,int type);
	
	public void deleteEntityByUserIdAndCpId(String userId,String cpId);
	
	public List< UserCollectionOrShare> getUserCollectionOrShareList(String userId,int type);
	
	public void deleteById(String id);
	
	
	
	
}
