package com.ryan.java.serviceprovider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DriverManager {

	private DriverManager(){
		
	}
	
	private static final Map<String, Driver> drivers = new ConcurrentHashMap<String, Driver>();
	
	public static void registerDriver(String name, Driver driver){
		drivers.put(name, driver);
	}
	
	public static Connection getConnection(String name){
		Driver driver = drivers.get(name);
		
		if(driver == null){
			throw new IllegalArgumentException(  
                    "No Driver registered with name:" + name);
		}
		
		return driver.getConnection();
	}
	
}
