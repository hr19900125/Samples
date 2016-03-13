package com.ryan.dao;

import java.util.List;

import com.ryan.model.Homework;

public interface HomeworkDao {

	public List<Homework> selectHomeworkByClassId(long classId);
	
}
