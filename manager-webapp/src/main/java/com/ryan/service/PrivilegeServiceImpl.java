package com.ryan.service;

import java.util.List;

import com.ryan.dao.PrivilegeDao;
import com.ryan.model.Privilege;

public class PrivilegeServiceImpl implements PrivilegeService{
	
	private PrivilegeDao privilegeDao;

	public void setPrivilegeDao(PrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}

	public long insertPrivilege(String type, String descr, String actionUrl) {
		Privilege p = new Privilege();
		p.setPrivilegeType(type);
		p.setPrivilegeDescr(descr);
		p.setPrivilegeAction(actionUrl);
	    privilegeDao.insertPrivilege(p);
	    return p.getID();
	}

	public boolean updatePrivilege(Privilege p) {
		return privilegeDao.updatePrivilege(p) == 1;
	}

	public List<Privilege> getAllPrivilege() {
		return privilegeDao.selectAllPrivilege();
	}

	public Privilege getPrivilege(long pid) {
		return privilegeDao.selectPrivilege(pid);
	}

	public int deletePrivilege(long pid) {
		return privilegeDao.deletePrivilege(pid);
	}

}
