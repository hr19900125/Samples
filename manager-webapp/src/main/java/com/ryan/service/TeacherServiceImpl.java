package com.ryan.service;

import java.util.List;

import com.ryan.dao.TeacherDao;
import com.ryan.model.Privilege;
import com.ryan.model.TeacherAccount;

public class TeacherServiceImpl implements TeacherService {

	private TeacherDao teacherDao;

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	@Override
	public TeacherAccount getTeacherAccountByUsername(String username) {
		return teacherDao.selectTeacherAccountByTelephone(username);
	}

	@Override
	public List<Privilege> getTeacherPrivileges(String username) {
		return teacherDao.selectTeacherPrivilege(username);
	}

	@Override
	public boolean updateTeacherAccount(TeacherAccount acc) {
		return teacherDao.updateTeacherAccount(acc) == 1;
	}

	@Override
	public List<Long> getTeacherRoleIDs(String telephone) {
		return teacherDao.selectTeacherRoleIDs(telephone);
	}

}
