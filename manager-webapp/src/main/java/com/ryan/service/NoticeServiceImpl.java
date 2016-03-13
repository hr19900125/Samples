package com.ryan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ryan.dao.NoticeDao;
import com.ryan.model.Notice;
import com.ryan.model.NoticeTarget;
import com.ryan.util.Pagination;

public class NoticeServiceImpl implements NoticeService{

	private static final int SCHOOL_TYPE = 1;
	private static final int CLASS_TYPE = 2;
	
	private NoticeDao noticeDao;
	
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	@Override
	public List<Notice> getSchoolNoticeList(int schoolId) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("type", SCHOOL_TYPE);
		map.put("target", schoolId);
		return noticeDao.getNoticeByTypeForTarget(map);
	}

	@Override
	public List<Notice> getClassNoticeList(int classId) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("type", CLASS_TYPE);
		map.put("target", classId);
		return noticeDao.getNoticeByTypeForTarget(map);
	}

	@Override
	public boolean addNotice(Notice notice) {
		return noticeDao.insertNotice(notice);
	}

	@Override
	public boolean deleteNotice(long id) {
		return noticeDao.deleteNotice(id);
	}

	@Override
	public boolean updateNotice(Notice notice) {
		return noticeDao.updateNotice(notice);
	}

	@Override
	public boolean addNoticeTarget(NoticeTarget nt) {
		return noticeDao.insertNoticeTarget(nt);
	}

	@Override
	public boolean deleteSchoolNoticeTargets(int schoolId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", SCHOOL_TYPE);
		map.put("targetID", schoolId);
		noticeDao.deleteNoticeTargets(map);
		return true;
	}

	@Override
	public boolean deleteClassNoticeTargets(int classId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("type", CLASS_TYPE);
		map.put("targetID", classId);
		noticeDao.deleteNoticeTargets(map);
		return true;
	}

	@Override
	public int getNoticeCount() {
		return noticeDao.getNoticeListCount();
	}

	@Override
	public List<Notice> getNoticeListByPage(Pagination p) {
		Map<String,Object> map = new HashMap<String, Object>();
		p.setTotalRecords(noticeDao.getNoticeListCount());
		map.put("index", p.getPageIndex());
		map.put("count", p.getPerPageSize());
		return noticeDao.getNoticeListByPage(map);
	}

	@Override
	public Notice getNotice(long id) {
		return noticeDao.selectNotice(id);
	}

}
