package com.ryan.dao;

import java.util.List;

import com.ryan.model.Privilege;
import com.ryan.model.Teacher;
import com.ryan.model.TeacherAccount;

public interface TeacherDao {

	public Teacher selectTeacherByTelephone(String telephone);
	
	public TeacherAccount selectTeacherAccountByTelephone(String telephone);
	
	public int updateTeacher(Teacher t);
	
	public int updateTeacherAccount(TeacherAccount ac);
	
	public int updateTeacherDeleted(String telephone);
	
	public void insertTeacherAccount(TeacherAccount ac);
	
	public void insertTeacher(Teacher t);
	
	public int deleteTeacherAccount(String telephone);
	
	public int deleteTeacher(String telephone);
	
	public List<Privilege> selectTeacherPrivilege(String telephone);
	
	public List<Long> selectTeacherRoleIDs(String telephone);
	
}
