package com.ryan.dao;

import java.util.List;

import com.ryan.model.InOutRecord;

public interface InOutRecordDao {

	public List<InOutRecord> getInOutRecordByTelephone(String telephone);
	
}
