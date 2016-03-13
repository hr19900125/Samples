package com.ryan.service;

import java.util.List;

import com.ryan.dao.ParentAccountDao;
import com.ryan.model.ParentAccount;
import com.ryan.model.Privilege;

public class ParentAccountServiceImpl implements ParentAccountService{

	private ParentAccountDao parentAccountDao;
	
	public void setParentAccountDao(ParentAccountDao parentAccountDao) {
		this.parentAccountDao = parentAccountDao;
	}

	@Override
	public ParentAccount getParentAccount(String username) {
		return parentAccountDao.selectParentAccountByUsername(username);
	}

	@Override
	public boolean updateParentAccount(ParentAccount pa) {
		return parentAccountDao.updateParentAccount(pa) == 1;
	}

	@Override
	public boolean deleteParentAccount(String username) {
		return parentAccountDao.deleteParentAccount(username) == 1;
	}

	@Override
	public List<Privilege> getParentPrivileges(String telephone) {
		return parentAccountDao.selectParentPrivileges(telephone);
	}

	@Override
	public List<Long> getParentRoleIDs(String telephone) {
		return parentAccountDao.selectParentRoleIDs(telephone);
	}

}
