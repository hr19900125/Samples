package com.ryan.dao;

import java.util.List;

import com.ryan.model.ParentAccount;
import com.ryan.model.Privilege;

public interface ParentAccountDao {

	public ParentAccount selectParentAccountByUsername(String username);
	
	public int updateParentAccount(ParentAccount pa);
	
	public int deleteParentAccount(String username);
	
	public List<Privilege> selectParentPrivileges(String telephone);
	
	public List<Long> selectParentRoleIDs(String telephone);
	
}
