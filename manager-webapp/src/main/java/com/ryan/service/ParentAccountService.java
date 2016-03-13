package com.ryan.service;

import java.util.List;

import com.ryan.model.ParentAccount;
import com.ryan.model.Privilege;

public interface ParentAccountService {

	public ParentAccount getParentAccount(String username);
	
	public boolean updateParentAccount(ParentAccount pa);
	
	public boolean deleteParentAccount(String username);
	
	public List<Privilege> getParentPrivileges(String telephone);
	
	public List<Long> getParentRoleIDs(String telephone);
	
}
