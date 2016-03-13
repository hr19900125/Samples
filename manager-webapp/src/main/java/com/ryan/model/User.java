package com.ryan.model;

import java.util.Date;

public class User {

	private String telephone; // 电话号码
	private String username; // 用户名
	private Date birthday; // 生日
	private String sex; // 性别
	private String email; // 邮箱
	private String qq; // qq号码
	private String deviceID; // 设备Id
	private String rfID; // rfID
	private Date createTime; // 创建时间
	private Date modifyTime; // 修改时间
	private String firstGuardianPhone; // 第一监护人手机
	private String secondGuardianPhone; // 第二监护人
	private String thirdGuardianPhone; //
	private long schoolID;
	private String schoolName;
	private long classID;
	private String className;

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getFirstGuardianPhone() {
		return firstGuardianPhone;
	}

	public void setFirstGuardianPhone(String firstGuardianPhone) {
		this.firstGuardianPhone = firstGuardianPhone;
	}

	public String getSecondGuardianPhone() {
		return secondGuardianPhone;
	}

	public void setSecondGuardianPhone(String secondGuardianPhone) {
		this.secondGuardianPhone = secondGuardianPhone;
	}

	public String getThirdGuardianPhone() {
		return thirdGuardianPhone;
	}

	public void setThirdGuardianPhone(String thirdGuardianPhone) {
		this.thirdGuardianPhone = thirdGuardianPhone;
	}

	public long getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(long schoolID) {
		this.schoolID = schoolID;
	}

	public long getClassID() {
		return classID;
	}

	public void setClassID(long classID) {
		this.classID = classID;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
