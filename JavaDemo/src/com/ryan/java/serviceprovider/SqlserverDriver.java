package com.ryan.java.serviceprovider;

public class SqlserverDriver implements Driver{

	static {
		DriverManager.registerDriver("sqlserver", new SqlserverDriver());
	}
	
	@Override
	public Connection getConnection() {
		return new SqlserverConnection();
	}

}
