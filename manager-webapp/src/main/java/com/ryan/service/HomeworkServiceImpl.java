package com.ryan.service;

import java.util.List;

import com.ryan.dao.HomeworkDao;
import com.ryan.model.Homework;

public class HomeworkServiceImpl implements HomeworkService{

	private HomeworkDao homeworkDao;

	public void setHomeworkDao(HomeworkDao homeworkDao) {
		this.homeworkDao = homeworkDao;
	}

	@Override
	public List<Homework> getHomeworkListByClassId(long classId) {
		return homeworkDao.selectHomeworkByClassId(classId);
	}

}
