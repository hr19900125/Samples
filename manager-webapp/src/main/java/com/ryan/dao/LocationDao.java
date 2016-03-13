package com.ryan.dao;

import java.util.List;
import java.util.Map;

import com.ryan.model.Location;

public interface LocationDao {

	public Location selectLastLocation(String imei);
	
	public List<Location> selectLocationListByDate(Map<String,Object> map);
	
}
