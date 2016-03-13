package com.ryan.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页器
 */
public class Pagination {
	
	private static final int DEFAULT_PERPAGE = 10;
	// 查询记录数
	private int totalRecords;
	// 第几页
	private int page;
	// 每页多少条记录
	private int perPageSize;

	public Pagination(){
		perPageSize = DEFAULT_PERPAGE;
	}
	
	// 总页数
	public int getTotalPages() {
		return (totalRecords + perPageSize - 1) / perPageSize;
	}

	// 首页
	public int getTopPage() {
		return 1;
	}

	// 上一页
	public int getPreviousPage() {
		if (page <= 1) {
			return 1;
		}
		return page - 1;
	}

	// 下一页
	public int getNextPage() {
		if (page >= getBottomPage()) {
			return getBottomPage();
		}
		return page + 1;
	}

	// 尾页
	public int getBottomPage() {
		return getTotalPages();
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPageSize() {
		return perPageSize;
	}

	public void setPerPageSize(int pageSize) {
		this.perPageSize = pageSize;
	}
	
	public int getPageIndex(){
		return ((getPage()-1)*getPerPageSize());
	}
	
	public int getTotalPage(){
		if(totalRecords % perPageSize == 0){
			return totalRecords/perPageSize;
		}else{
			return totalRecords/perPageSize + 1;
		}
	}
	
	public List<Integer> getPages(){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 1;i <= getTotalPages();i++){
			list.add(i);
		}
		return list;
	}
	
	public List<Integer> getPages(int number){
		List<Integer> list = new ArrayList<Integer>();
		int mid = number/2;
		int left,right;
		if(number%2 == 1){
			left = getPage() - mid;
			right = getPage() + mid;
		}else{
			left = getPage() - mid;
			right = getPage() + mid-1;
		}
		if(left < 1){
			right += 1 - left;
			left = 1;
			
			if(right > getTotalPages()){
				right = getTotalPages();
			}
		}
		
		if(right > getTotalPages()){
		    left -= right - getTotalPages();
		    right = getTotalPages();
		    if(left < 1){
		    	left = 1;
		    }
		}
		
		for(int i=left;i<=right;i++){
			list.add(i);
		}
			
		return list;
	}
	
}
