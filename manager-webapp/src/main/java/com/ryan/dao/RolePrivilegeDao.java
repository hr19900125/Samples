package com.ryan.dao;

import java.util.List;

import com.ryan.model.Privilege;
import com.ryan.model.Role;
import com.ryan.model.RolePrivilege;

public interface RolePrivilegeDao {

	/**
	 * 插入权限与角色关系
	 * @param rp
	 */
	public void insertPrivilegeRole(RolePrivilege rp);
	
	/**
	 * 获取角色所有权限
	 * @param roleID
	 * @return
	 */
	public List<Privilege> selectPrivileges(long roleID);
	
	/**
	 * 删除关联
	 * @param privilegeID
	 */
	public void deleteRolePrivilegeByPid(long privilegeID);
	
	/**
	 * 删除关联
	 * @param roleID
	 */
	public void deleteRolePrivilegeByRid(long roleID);
	
	/**
	 * 
	 * @param privilegeID
	 * @return
	 */
	public List<RolePrivilege> selectRolePrivilegesByPid(long privilegeID);
	
//	/**
//	 * 根据roleID获取该角色所有权限
//	 * @param roleID
//	 */
//	public List<Privilege> selectPrivilegeListByRid(long roleID);
}
