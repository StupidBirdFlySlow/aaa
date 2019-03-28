package edu.hunau.base.service;

import java.util.List;

import edu.hunau.base.model.User;

public interface UserService {

	public void addUser(User user);

	public void deleteUserById(String id);

	public void updateUser(User user);

	public User queryUserById(String id);
	
	public boolean checkEmail(String email);
	
	public boolean checkPhone(String phone);

	public User getUser(User user);
	
	public List<User> getEntityList(int pageNum,int pageSize);
	
	public int getTotalCountOfUser();
	
}
