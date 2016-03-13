package com.ryan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ryan.dao.RoleDao;
import com.ryan.model.Role;
import com.ryan.util.Pagination;

public class RoleServiceImpl implements RoleService{

	private RoleDao roleDao;
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public List<Role> getRoles(String searchName, Pagination p) {
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("searchName", searchName);
		map.put("index", p.getPageIndex());
		map.put("count", p.getPerPageSize());
		p.setTotalRecords(roleDao.selectRolesCount());
		return roleDao.selectRoles(map);
	}

	public List<Role> getAllRoles() {
		return roleDao.selectAllRoles();
	}
	
	public Role getRole(long roleID) {
		return roleDao.selectRole(roleID);
	}

	public void insertRole(Role role) {
	    roleDao.insertRole(role);
	}

	public int updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	public int deleteRole(long roleID) {
		return roleDao.deleteRole(roleID);
	}

	public int deleteRoles(List<String> roleIDs) {
		return roleDao.deleteRoles(roleIDs);
	}

}
