package com.ryan.dao;

import java.util.List;
import java.util.Map;

import com.ryan.model.Role;

public interface RoleDao {

	/**
	 * 分页获取角色列表
	 * @param map
	 * @return
	 */
	public List<Role> selectRoles(Map<String ,Object> map);
	
	/**
	 * 获取所有角色列表
	 * @return
	 */
	public List<Role> selectAllRoles();
	
	/**
	 * 根据ID获取角色
	 * @param roleID
	 * @return
	 */
	public Role selectRole(long roleID);
	
	/**
	 * 获取所有role记录数
	 * @return
	 */
	public int selectRolesCount();
	
	/**
	 * 创建角色
	 * @param role
	 * @return
	 */
	public void insertRole(Role role);
	
	/**
	 * 修改角色信息
	 * @param role
	 * @return
	 */
	public int updateRole(Role role);
	
	/**
	 * 删除一个角色
	 * @param roleID
	 * @return
	 */
	public int deleteRole(long roleID);
	
	/**
	 * 删除角色列表
	 * @param roleIDs
	 * @return
	 */
	public int deleteRoles(List<String> roleIDs);
}
