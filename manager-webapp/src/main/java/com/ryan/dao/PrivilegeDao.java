package com.ryan.dao;

import java.util.List;

import com.ryan.model.Privilege;

public interface PrivilegeDao {

	/**
	 * 获取所有权限
	 * @return
	 */
	public List<Privilege> selectAllPrivilege();
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Privilege selectPrivilege(long id);
	
	/**
	 * 新增权限
	 * @param p
	 * @return
	 */
	public void insertPrivilege(Privilege p);
	
	/**
	 * 修改权限
	 * @param p
	 * @return
	 */
	public int updatePrivilege(Privilege p);
	
	/**
	 * 删除权限
	 * @param pid
	 * @return
	 */
	public int deletePrivilege(long pid);
}
