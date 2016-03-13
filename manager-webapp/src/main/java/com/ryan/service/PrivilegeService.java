package com.ryan.service;

import java.util.List;

import com.ryan.model.Privilege;

public interface PrivilegeService {

	public long insertPrivilege(String type,String descr,String actionUrl);
	
	public boolean updatePrivilege(Privilege p);
	
	public List<Privilege> getAllPrivilege();
	
	public Privilege getPrivilege(long pid);
	
	public int deletePrivilege(long pid);
}
