package com.ryan.service;

import com.ryan.dao.TestMapper;
import com.ryan.model.TestBean;

public class TestServiceImpl implements TestService{

	private TestMapper testDao;
	public TestMapper getTestDao() {
		return testDao;
	}
	public void setTestDao(TestMapper testDao) {
		this.testDao = testDao;
	}

	public TestBean getTestInfo() {
		return testDao.getTest();
	}

	
}
