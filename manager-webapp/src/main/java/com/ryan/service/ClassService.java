package com.ryan.service;

import java.util.List;
import com.ryan.model.Class;
import com.ryan.util.Pagination;

public interface ClassService {

	public boolean addClass(Class c);

	public boolean deleteClass(long id);

	public List<Class> getClassListByPage(Pagination p);
	
	public List<Class> getClassListBySchoolId(long schoolId);

	public Class getClassById(long id);

	public boolean udpateClass(Class c);
	
	public int setClassDeleted(long schoolId);
	
	public boolean setClassDeletedByClassId(long classId);
	
	public List<Class> getClassListByTeacherId(String teacherId);
}
