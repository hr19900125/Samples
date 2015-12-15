package com.ryan.java.serviceprovider;
/**
 * 服务提供者框架（Service Provider Framework）
 * 
 * 疑惑：关于省略服务提供者接口（Driver）的问题，因为Connection是每次连接都需要新生成，所以可以看做Driver是Connection的工厂类
 * 假如服务接口（Connection）是单例的，可以考虑省略Driver
 *
 */
public class Test {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
        Class.forName("com.ryan.java.serviceprovider.MysqlDriver");
        Connection conn = DriverManager.getConnection("mysql");
        conn.connect();
	}

}
