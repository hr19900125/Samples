package com.ryan.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ryan.dao.LocationDao;
import com.ryan.model.Location;
import com.ryan.util.DateUtil;

public class LocationServiceImpl implements LocationService{

	private LocationDao locationDao;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	@Override
	public Location getLastLocation(String imei) {
		return locationDao.selectLastLocation(imei);
	}

	@Override
	public List<Location> getLocationList(String imei, Date beginTime,Date endTime) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("imei", imei);
		map.put("beginTime", DateUtil.format(beginTime, "yyyy-MM-dd HH:mm:ss"));
		map.put("endTime", DateUtil.format(beginTime, "yyyy-MM-dd HH:mm:ss"));
		return locationDao.selectLocationListByDate(map);
	}

}
