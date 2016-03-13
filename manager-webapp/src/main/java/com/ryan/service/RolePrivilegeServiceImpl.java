package com.ryan.service;

import java.util.List;

import com.ryan.dao.RolePrivilegeDao;
import com.ryan.model.Privilege;
import com.ryan.model.RolePrivilege;

public class RolePrivilegeServiceImpl implements RolePrivilegeService {

	private RolePrivilegeDao rolePrivilegeDao;

	public void setRolePrivilegeDao(RolePrivilegeDao rolePrivilegeDao) {
		this.rolePrivilegeDao = rolePrivilegeDao;
	}

	public void addRolePrivilege(long roleID, long privilegeID) {
        RolePrivilege rp = new RolePrivilege();
        rp.setRoleID(roleID);
        rp.setPrivilegeID(privilegeID);
        rolePrivilegeDao.insertPrivilegeRole(rp);
	}

	public void deleteRolePrivilegeByRid(long roleID) {
		rolePrivilegeDao.deleteRolePrivilegeByRid(roleID);
	}

	public void deleteRolePrivilegeByPid(long privilegeID) {
		rolePrivilegeDao.deleteRolePrivilegeByPid(privilegeID);
	}

	public List<Privilege> getPrivilegeByRoleID(long roleID) {
		return rolePrivilegeDao.selectPrivileges(roleID);
	}

	@Override
	public List<RolePrivilege> getRolePrivilegesByPid(long privilegeID) {
		return rolePrivilegeDao.selectRolePrivilegesByPid(privilegeID);
	}

}
