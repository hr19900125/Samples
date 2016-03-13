package com.ryan.model;

public class NoticeTarget {

	private long noticeID;
	private int targetType;
	private long targetID;

	public long getNoticeID() {
		return noticeID;
	}

	public void setNoticeID(long noticeID) {
		this.noticeID = noticeID;
	}

	public int getTargetType() {
		return targetType;
	}

	public void setTargetType(int targetType) {
		this.targetType = targetType;
	}

	public long getTargetID() {
		return targetID;
	}

	public void setTargetID(long targetID) {
		this.targetID = targetID;
	}

}
