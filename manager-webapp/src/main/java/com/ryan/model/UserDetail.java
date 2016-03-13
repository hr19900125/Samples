package com.ryan.model;

import java.util.List;

/**
 * 详细用户信息
 */
public class UserDetail {

	private User user;
	private List<Role> roles;
	private List<Privilege> privileges;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

}
