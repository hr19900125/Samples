package com.ryan.service;

import java.util.Date;
import java.util.List;

import com.ryan.model.Location;

public interface LocationService {

	public Location getLastLocation(String imei);
	
	List<Location> getLocationList(String imei,Date beginTime,Date endTime);
	
}
