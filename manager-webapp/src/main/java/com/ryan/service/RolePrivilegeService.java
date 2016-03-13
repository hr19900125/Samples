package com.ryan.service;

import java.util.List;

import com.ryan.model.Privilege;
import com.ryan.model.RolePrivilege;

public interface RolePrivilegeService {

	/**
	 * 
	 * @param roleID
	 * @param privilegeID
	 */
	public void addRolePrivilege(long roleID ,long privilegeID);
	
	/**
	 * 
	 */
	public void deleteRolePrivilegeByRid(long roleID);
	
	/**
	 * 
	 * @param privilegeID
	 */
	public void deleteRolePrivilegeByPid(long privilegeID);
	
	/**
	 * 根据roleID获取该用户所有权限
	 * @param roleID
	 * @return
	 */
	public List<Privilege> getPrivilegeByRoleID(long roleID);
	
	/**
	 * 
	 * @param privilegeID
	 * @return
	 */
	public List<RolePrivilege> getRolePrivilegesByPid(long privilegeID);
	
}
