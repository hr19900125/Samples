package com.ryan.service;

import java.util.List;

import com.ryan.model.InOutRecord;

public interface InOutRecordService {

	public List<InOutRecord> getStudentInOutRecord(String telephone);
	
}
