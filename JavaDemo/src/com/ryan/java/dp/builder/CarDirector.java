package com.ryan.java.dp.builder;

public class CarDirector {

	private CarBuilder carBuilder;
	
	public CarDirector(CarBuilder carBuilder){
		this.carBuilder = carBuilder;
	}
	
	/**
	 * 组装汽车
	 */
	public void assembleCar(){
		carBuilder.assembleEngine();
		carBuilder.assembleTurbine();
		carBuilder.assembleTyre();
	}
	
}
