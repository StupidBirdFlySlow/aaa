package edu.hunau.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hunau.base.dao.UserCollectionOrShareDao;
import edu.hunau.base.model.UserCollectionOrShare;
import edu.hunau.base.service.UserCollectionOrShareService;

@Service("userCollectionOrShareService")
@Transactional
public class UserCollectionOrShareServiceImpl implements UserCollectionOrShareService{

	@Autowired
	@Qualifier("userCollectionOrShareDao")
	private UserCollectionOrShareDao userCollectionOrShareDao;
	
	public void addRecords(UserCollectionOrShare entity) {
		userCollectionOrShareDao.addRecords(entity);
	}

	public List<String> getCpIdsByUserId(String userId, int type) {
		return userCollectionOrShareDao.getCollectionCpIdsByUserId(userId, type);
	}

	public int getTotalCountByUserId(String userId, int type) {
		return userCollectionOrShareDao.getTotalCountOfUsers(userId, type);
	}

	public int getCollectionCountByCpId(String cpId) {
		return userCollectionOrShareDao.getCollectionCountByCpId(cpId);
	}

	public void deleteEntityByUserIdAndCpId(String userId,String cpId) {
		userCollectionOrShareDao.deleteEntityByUserIdAndCpId(userId, cpId);
	}

	public List<UserCollectionOrShare> getUserCollectionOrShareList(String userId, int type) {
		return userCollectionOrShareDao.getUserCollectionOrShareList(userId, type);
	}

	public void deleteById(String id) {
		userCollectionOrShareDao.deleteById(id);
	}

	
}
