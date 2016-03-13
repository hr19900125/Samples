package com.ryan.dao;

import java.util.List;

import com.ryan.model.User;
import com.ryan.model.UserQuery;

public interface UserDao {

	/**
	 * 增加用户
	 * @return boolean
	 */
	public void insertUser(User user);
	
	/**
	 * 更新用户
	 * 根据用户Id进行更新
	 * @return boolean
	 */
	public int updateUser(User user);
	
	/**
	 * 获取用户数据
	 * @return User
	 */
	public User selectUserByTelephone(String telephone);
	
	/**
	 * 获取用户列表
	 * @param sortType 排序方式 []
	 * @param index 开始位置
	 * @param count 获取多少个
	 * @return List<User>
	 */
    public List<User> selectUserList(UserQuery query);
	
	/**
	 * 删除用户
	 * @param userId
	 * @return boolean
	 */
	public boolean deleteUser(String telephone);
	
	/**
	 * 
	 * @return
	 */
	public int updateUserStatusDeleted(String telephone); 
	
	/**
	 * 获取所有用户个数
	 * @return 
	 */
	public int selectAllUsersCount();
	
	/**
	 * 根据监护人获取用户信息
	 */
	public List<User> selectUserByFirstGuardianPhone(String phone);

}
