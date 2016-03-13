package com.ryan.service;

import java.util.List;

import com.ryan.model.User;
import com.ryan.util.Pagination;

public interface UserService {

	public boolean insertUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(String telephone);
	
	public int setUserDeleted(String telephone);
	
	public User getUser(String telephone);
	
	public List<User> getUserListByPage(int sortType, Pagination p);
	
	public List<User> getUserByFirstGuardianPhone(String phone);
	
}
