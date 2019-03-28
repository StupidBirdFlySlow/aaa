package edu.hunau.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hunau.base.dao.UserDao;
import edu.hunau.base.model.User;
import edu.hunau.base.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void deleteUserById(String id) {
		// TODO Auto-generated method stub

	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public User queryUserById(String id) {
		return userDao.queryUserById(id);
	}

	public boolean checkEmail(String email) {
		return userDao.checkEmail(email);
	}

	public boolean checkPhone(String phone) {
		return userDao.checkPhone(phone);
	}

	public User getUser(User user) {
		return userDao.getUser(user);
	}

	public List<User> getEntityList(int pageNum, int pageSize) {
		return userDao.getEntityList(pageNum, pageSize);
	}

	public int getTotalCountOfUser() {
		return userDao.getTotalCountOfUser();
	}

}
