package com.ryan.service;

import java.util.List;

import com.ryan.model.Notice;
import com.ryan.model.NoticeTarget;
import com.ryan.util.Pagination;

public interface NoticeService {

	public Notice getNotice(long id);
	
	public boolean addNotice(Notice notice);
	
	public boolean deleteNotice(long id);
	
	public boolean updateNotice(Notice notice);
	
	public boolean addNoticeTarget(NoticeTarget nt);
	
	public boolean deleteSchoolNoticeTargets(int schoolId);
	
	public boolean deleteClassNoticeTargets(int classId);
	
	public List<Notice> getSchoolNoticeList(int schoolId);
	
	public List<Notice> getClassNoticeList(int classId);
	
	public int getNoticeCount();
	
	public List<Notice> getNoticeListByPage(Pagination p);
	
}
