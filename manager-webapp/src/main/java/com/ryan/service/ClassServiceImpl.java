package com.ryan.service;

import java.util.List;

import com.ryan.dao.ClassDao;
import com.ryan.model.Class;
import com.ryan.model.ClassQuery;
import com.ryan.util.Pagination;

public class ClassServiceImpl implements ClassService{

	private ClassDao classDao;
	
	public void setClassDao(ClassDao classDao) {
		this.classDao = classDao;
	}

	@Override
	public boolean addClass(Class c) {
		classDao.insertClass(c);
		if(c.getID() > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deleteClass(long id) {
		return setClassDeletedByClassId(id);
	}

	@Override
	public List<Class> getClassListByPage(Pagination p) {
		ClassQuery cq = new ClassQuery();
		cq.setIndex(p.getPageIndex());
		cq.setCount(p.getPerPageSize());
		p.setTotalRecords(classDao.selectAllClassCount());
		return classDao.selectClassPage(cq);
	}

	@Override
	public Class getClassById(long id) {
		return classDao.selectClassById(id);
	}

	@Override
	public boolean udpateClass(Class c) {
		return classDao.updateClass(c);
	}

	@Override
	public int setClassDeleted(long schoolId) {
		return classDao.updateClassDeletedBySchoolId(schoolId);
	}

	@Override
	public boolean setClassDeletedByClassId(long classId) {
		return classDao.updateClassDeletedByClassId(classId) == 1;
	}

	@Override
	public List<Class> getClassListByTeacherId(String teacherId) {
		return classDao.selectClassesByTeacherId(teacherId);
	}

	@Override
	public List<Class> getClassListBySchoolId(long schoolId) {
		return classDao.selectClassesBySchoolId(schoolId);
	}

}
