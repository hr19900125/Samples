package com.ryan.java.dp.builder;

public class BmwCar extends Car{
	
	public BmwCar(String engine, String turbine, String tyre){
		this.engine = engine;
		this.turbine = turbine;
		this.tyre = tyre;
	}
	
	@Override
	public void printCarInfo() {
		// TODO Auto-generated method stub
		System.out.printf("engine[%s],turbine[%s],tyre[%s]\n",engine,turbine,tyre);
	}

}
