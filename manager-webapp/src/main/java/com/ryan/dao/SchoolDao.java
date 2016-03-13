package com.ryan.dao;

import java.util.List;

import com.ryan.model.School;
import com.ryan.model.SchoolQuery;

public interface SchoolDao {

	/**
	 * 分页获取学校列表
	 * @param query
	 * @return
	 */
	public List<School> selectSchoolPage(SchoolQuery query);
	
	/**
	 * 
	 * @return
	 */
	public List<School> selectAllSchool();
	/**
	 * 获取所有记录数
	 * @return
	 */
	public int selectAllSchoolCount();
	
	/**
	 * 
	 */
	public School selectSchoolById(long id);
	
	/**
	 * 插入学校
	 */
	public void insertSchool(School school);
	
	/**
	 * 更新学校
	 * @param school
	 * @return
	 */
	public boolean updateSchool(School school);
	
	/**
	 * @param id
	 * @return
	 */
	public int updateSchoolDeleted(long id);
	
	/**
	 * 删除学校
	 * @param id
	 * @return
	 */
	public int deleteSchool(long id);
}
