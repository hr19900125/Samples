package com.ryan.service;

import java.util.List;

import com.ryan.dao.UserDao;
import com.ryan.model.User;
import com.ryan.model.UserQuery;
import com.ryan.util.Pagination;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao mUserDao) {
		this.userDao = mUserDao;
	}

	public boolean insertUser(User user) {
		userDao.insertUser(user);
		return true;
	}

	public boolean updateUser(User user) {
		int cnt = userDao.updateUser(user);
		if (cnt > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteUser(String telephone) {
//		return userDao.deleteUser(id);
		return userDao.updateUserStatusDeleted(telephone) == 1;
	}

	public User getUser(String telephone) {
		return userDao.selectUserByTelephone(telephone);
	}

	public List<User> getUserListByPage(int sortType, Pagination p) {
		UserQuery query = new UserQuery();
		query.setIndex(p.getPageIndex());
		query.setCount(p.getPerPageSize());
		query.setSortType(sortType);
		p.setTotalRecords(userDao.selectAllUsersCount());
		return userDao.selectUserList(query);
	}

	@Override
	public int setUserDeleted(String telephone) {
		return userDao.updateUserStatusDeleted(telephone);
	}

	@Override
	public List<User> getUserByFirstGuardianPhone(String phone) {
		return userDao.selectUserByFirstGuardianPhone(phone);
	}
	
}
