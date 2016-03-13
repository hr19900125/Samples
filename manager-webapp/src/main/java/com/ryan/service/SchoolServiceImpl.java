package com.ryan.service;

import java.util.List;

import com.ryan.dao.SchoolDao;
import com.ryan.model.School;
import com.ryan.model.SchoolQuery;
import com.ryan.util.Pagination;

public class SchoolServiceImpl implements SchoolService{
	
	private SchoolDao schoolDao;
	
	public void setSchoolDao(SchoolDao schoolDao) {
		this.schoolDao = schoolDao;
	}

	@Override
	public boolean addSchool(School school) {
		schoolDao.insertSchool(school);
		if(school.getID() > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deleteSchool(long id) {
//		return schoolDao.deleteSchool(id) == 1;
		return setSchoolDeleted(id);
	}

	@Override
	public List<School> getSchoolListByPage(Pagination p) {
		SchoolQuery sq = new SchoolQuery();
		sq.setIndex(p.getPageIndex());
		sq.setCount(p.getPerPageSize());
		p.setTotalRecords(schoolDao.selectAllSchoolCount());
		return schoolDao.selectSchoolPage(sq);
	}

	@Override
	public boolean udpateSchool(School school) {
		return schoolDao.updateSchool(school);
	}

	@Override
	public School getSchoolById(long id) {
		return schoolDao.selectSchoolById(id);
	}

	@Override
	public List<School> getAllSchool() {
		return schoolDao.selectAllSchool();
	}

	@Override
	public boolean setSchoolDeleted(long id) {
		return schoolDao.updateSchoolDeleted(id) == 1;
	}

}
