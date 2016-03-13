package com.ryan.service;

import java.util.List;

import com.ryan.model.Homework;

public interface HomeworkService {

	public List<Homework> getHomeworkListByClassId(long classId); 
	
}
