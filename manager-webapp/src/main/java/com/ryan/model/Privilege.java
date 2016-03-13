package com.ryan.model;

public class Privilege {

	private long ID; // 权限ID
	private String privilegeType; // 权限类型
	private String privilegeDescr; // 权限描述
	private String privilegeAction; // 权限限制的url
	private boolean selected; // 是否被选择

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public long getID() {
		return ID;
	}

	public void setID(long id) {
		ID = id;
	}

	public String getPrivilegeType() {
		return privilegeType;
	}

	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}

	public String getPrivilegeDescr() {
		return privilegeDescr;
	}

	public void setPrivilegeDescr(String privilegeDescr) {
		this.privilegeDescr = privilegeDescr;
	}

	public String getPrivilegeAction() {
		return privilegeAction;
	}

	public void setPrivilegeAction(String privilegeAction) {
		this.privilegeAction = privilegeAction;
	}

}
