package com.ryan.java.serviceprovider;

public class MysqlDriver implements Driver{

	static {
		DriverManager.registerDriver("mysql", new MysqlDriver());
	}
	
	@Override
	public Connection getConnection() {
		return new MysqlConnection();
	}

	
}
