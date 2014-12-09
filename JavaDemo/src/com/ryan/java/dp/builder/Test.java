package com.ryan.java.dp.builder;
/**
 * 建造者模式 
 *
 */
public class Test {

	public static void main(String[] args){
		BmwCarBuilder bmwCarBuilder = new BmwCarBuilder();
		BydCarBuilder bydCarBuilder = new BydCarBuilder();
		CarDirector bmwCarDirector = new CarDirector(bmwCarBuilder);
		bmwCarDirector.assembleCar();
		Car bmwCar = bmwCarBuilder.produceCar();
		bmwCar.printCarInfo();
		
		CarDirector bydCarDirector = new CarDirector(bydCarBuilder);
		bydCarDirector.assembleCar();
		Car bydCar = bydCarBuilder.produceCar();
		bydCar.printCarInfo();
		
	}
}
