package com.ryan.web.open.model;

import java.util.Date;

public class OUser {

	private String telephone; // 电话号码
	private String username; // 用户名
	private Date birthday; // 生日
	private String sex; // 性别
	private String aliasname; // 别名
	private String email; // 邮箱
	private String qq; // qq号码
	private String deviceID; // 设备Id
	private String rfID; // rfID

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAliasname() {
		return aliasname;
	}

	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getRfID() {
		return rfID;
	}

	public void setRfID(String rfID) {
		this.rfID = rfID;
	}

}
