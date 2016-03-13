package com.ryan.dao;

import java.util.List;

import com.ryan.model.Class;
import com.ryan.model.ClassQuery;

public interface ClassDao {

	/**
	 * 分页获取班级列表
	 * @param query
	 * @return List
	 */
	public List<Class> selectClassPage(ClassQuery query);
	
	/**
	 * 
	 * @param schoolId
	 * @return
	 */
	public List<Class> selectClassesBySchoolId(long schoolId);
	
	/**
	 * 
	 * @return
	 */
	public int selectAllClassCount();
	
	/**
	 * 
	 * @return
	 */
	public Class selectClassById(long id);
	
	/**
	 * 
	 * @param c
	 */
	public void insertClass(Class c);
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean updateClass(Class c);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int updateClassDeletedBySchoolId(long id);
	
	/**
	 * 
	 * @return
	 */
    public int updateClassDeletedByClassId(long classId);	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int deleteClass(long id);
	
	/**
	 * @param teacherId
	 * @return
	 */
	public List<Class> selectClassesByTeacherId(String teacherId);
}
