package com.ryan.web.open.mobile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryan.http.HttpResult;
import com.ryan.model.Location;
import com.ryan.service.LocationService;
import com.ryan.util.DateUtil;
import com.ryan.util.ResultCode;
import com.ryan.web.open.model.OLocation;

@Controller
public class LocationApi {

	public static final int DATE_DAY = 1;
	
	public static final int DATE_WEEK = 2;
	
	public static final int DATE_LASTTIME = 3;
	
	@Autowired
	private LocationService locationService;
	
	private GsonBuilder builder = new GsonBuilder();
	
	@RequestMapping("/open/getLocationInfo")
	public @ResponseBody String getLocationInfo(@RequestParam("imei")String imei,@RequestParam(value="type",defaultValue="3") int type){
		Gson gson = builder.create();
		HttpResult result = new HttpResult();
		if(type == DATE_LASTTIME){
		    Location loc = locationService.getLastLocation(imei);
			if(loc == null){
				result.setResultCode(ResultCode.RESULT_OK);
				result.setResultValue(null);
				return gson.toJson(result);
			}else{
				OLocation oLoc = new OLocation();
				oLoc.setLat(loc.getLat());
				oLoc.setLng(loc.getLng());
				oLoc.setTime(loc.getAcceptTime());
				result.setResultCode(ResultCode.RESULT_OK);
				result.setResultValue(oLoc);
				return gson.toJson(result);
			}
		}else if(type == DATE_DAY){
			Calendar c = Calendar.getInstance();
			Date date = DateUtil.now();
			c.setTime(date);  
	        int day = c.get(Calendar.DATE);  
	        c.set(Calendar.DATE, day - 1); 
		    List<Location> locList = locationService.getLocationList(imei, c.getTime(), date);
		    List<OLocation> oLocList = new ArrayList<OLocation>();
		    if(locList != null){
		    	OLocation oLoc = null;
		    	for(Location loc : locList){
		    		oLoc = new OLocation();
		    		oLoc.setLat(loc.getLat());
		    		oLoc.setLng(loc.getLng());
		    		oLoc.setTime(loc.getAcceptTime());
		    		oLocList.add(oLoc);
		    	}
		    }
		    
		    result.setResultCode(ResultCode.RESULT_OK);
		    result.setResultValue(oLocList);
		    return gson.toJson(result);
		}else if(type == DATE_WEEK){
			Calendar c = Calendar.getInstance();
			Date date = DateUtil.now();
			c.setTime(date);  
	        int day = c.get(Calendar.DATE);  
	        c.set(Calendar.DATE, day - 7); 
		    List<Location> locList = locationService.getLocationList(imei, c.getTime(), date);
		    List<OLocation> oLocList = new ArrayList<OLocation>();
		    if(locList != null){
		    	OLocation oLoc = null;
		    	for(Location loc : locList){
		    		oLoc = new OLocation();
		    		oLoc.setLat(loc.getLat());
		    		oLoc.setLng(loc.getLng());
		    		oLoc.setTime(loc.getAcceptTime());
		    		oLocList.add(oLoc);
		    	}
		    }
		    
		    result.setResultCode(ResultCode.RESULT_OK);
		    result.setResultValue(oLocList);
		    return gson.toJson(result);
		}
		return null;
	}
	
}
