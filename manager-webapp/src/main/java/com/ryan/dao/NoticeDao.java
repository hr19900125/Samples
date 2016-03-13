package com.ryan.dao;

import java.util.List;
import java.util.Map;

import com.ryan.model.Notice;
import com.ryan.model.NoticeTarget;

public interface NoticeDao {
	
	public Notice selectNotice(long id);

	public boolean insertNotice(Notice notice);
	
	public boolean deleteNotice(long id);
	
	public boolean updateNotice(Notice notice);
	
	public boolean insertNoticeTarget(NoticeTarget nt);
	
	public int deleteNoticeTargets(Map<String,Object> map);
	
	public List<Notice> getNoticeByTypeForTarget(Map<String,Object> map);
	
	public List<Notice> getNoticeListByPage(Map<String,Object> map);
	
	public int getNoticeListCount();
	
}
