package com.ryan.service;

import java.util.List;

import com.ryan.model.Privilege;
import com.ryan.model.TeacherAccount;

public interface TeacherService {

	public TeacherAccount getTeacherAccountByUsername(String username);
	
	public List<Privilege> getTeacherPrivileges(String telephone);
	
	public boolean updateTeacherAccount(TeacherAccount acc);
	
	public List<Long> getTeacherRoleIDs(String telephone);
}
