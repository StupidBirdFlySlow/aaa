package edu.hunau.base.service;

import java.util.List;

import edu.hunau.base.model.UserCollectionOrShare;

public interface UserCollectionOrShareService {

	public void addRecords(UserCollectionOrShare entity);
	
	/**
	 * ����û��ղػ����Ĳ��ױ��
	 * @param userId
	 * @param type
	 * @return
	 */
	public List<String> getCpIdsByUserId(String userId,int type);
	
	/**
	 * ����û��ղػ������ܼ�¼��
	 * @param userId
	 * @param type
	 * @return
	 */
	public int getTotalCountByUserId(String userId,int type);
	
	/**
	 * ��ò��׵��ղ���
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
