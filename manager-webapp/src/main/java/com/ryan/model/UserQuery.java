package com.ryan.model;

public class UserQuery {

	public static final int SORT_BY_NAME = 1;
	public static final int SORT_BY_ID = 2;
	public static final int SORT_BY_CREATETIME = 3;
	public static final int SORT_BY_MODIFYTIME = 4;

	private int sortType;
	private int index;
	private int count;

	public int getSortType() {
		return sortType;
	}

	public void setSortType(int sortType) {
		this.sortType = sortType;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
