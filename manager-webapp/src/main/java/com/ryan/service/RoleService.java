package com.ryan.service;

import java.util.List;

import com.ryan.model.Role;
import com.ryan.util.Pagination;

public interface RoleService {

	public List<Role> getRoles(String roleName ,Pagination p);
	
	public List<Role> getAllRoles();
	
	public Role getRole(long roleID);
	
	public void insertRole(Role role);
	
	public int updateRole(Role role);
	
	public int deleteRole(long roleID);
	
	public int deleteRoles(List<String> roleIDs);
	
}
