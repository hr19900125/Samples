package com.ryan.service;

import java.util.List;

import com.ryan.model.School;
import com.ryan.util.Pagination;

public interface SchoolService {

	public boolean addSchool(School school);
	
	public boolean deleteSchool(long id);
	
	public boolean setSchoolDeleted(long id);
	
	public List<School> getSchoolListByPage(Pagination p);
	
	public List<School> getAllSchool();
	
	public School getSchoolById(long id);
	
	public boolean udpateSchool(School school);
}
