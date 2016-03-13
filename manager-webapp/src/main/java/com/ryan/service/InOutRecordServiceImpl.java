package com.ryan.service;

import java.util.List;

import com.ryan.dao.InOutRecordDao;
import com.ryan.model.InOutRecord;

public class InOutRecordServiceImpl implements InOutRecordService {

	private InOutRecordDao inOutRecordDao;

	public void setInOutRecordDao(InOutRecordDao inOutRecordDao) {
		this.inOutRecordDao = inOutRecordDao;
	}

	@Override
	public List<InOutRecord> getStudentInOutRecord(String telephone) {
		return inOutRecordDao.getInOutRecordByTelephone(telephone);
	}

}
