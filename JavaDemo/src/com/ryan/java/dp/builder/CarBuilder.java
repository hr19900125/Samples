package com.ryan.java.dp.builder;
/**
 * car的builder的接口 
 *
 */
public interface CarBuilder {

	/**
	 * 组装发动机
	 */
	public void assembleEngine();
	
	/**
	 * 组装涡轮
	 */
	public void assembleTurbine();
	
	/**
	 * 组装轮胎
	 */
	public void assembleTyre();
	
	/**
	 * 生产小车车
	 */
	public Car produceCar();
	
}
