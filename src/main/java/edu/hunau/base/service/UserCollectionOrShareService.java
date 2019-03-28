package edu.hunau.base.service;

import java.util.List;

import edu.hunau.base.model.UserCollectionOrShare;

public interface UserCollectionOrShareService {

	public void addRecords(UserCollectionOrShare entity);
	
	/**
	 * 获得用户收藏或分享的菜谱编号
	 * @param userId
	 * @param type
	 * @return
	 */
	public List<String> getCpIdsByUserId(String userId,int type);
	
	/**
	 * 获得用户收藏或分享的总记录数
	 * @param userId
	 * @param type
	 * @return
	 */
	public int getTotalCountByUserId(String userId,int type);
	
	/**
	 * 获得菜谱的收藏量
	 * @param cpId
	 * @return
	 */
	public int getCollectionCountByCpId(String cpId);
	
	
	public void deleteEntityByUserIdAndCpId(String userId,String cpId);
	
	
	/**
	 * 
	 */
	public List<UserCollectionOrShare> getUserCollectionOrShareList(String userId,int type);
	
	public void deleteById(String id);
	
}
