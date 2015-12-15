package com.ryan.java.dp.builder;

public class BydCarBuilder implements CarBuilder{

	private String engine;
    private String turbine;
    private String tyre;
	
	@Override
	public void assembleEngine() {
		// TODO Auto-generated method stub
		engine = "byd engine";
	}

	@Override
	public void assembleTurbine() {
		// TODO Auto-generated method stub
		turbine = "byd turbine";
	}

	@Override
	public void assembleTyre() {
		// TODO Auto-generated method stub
		tyre = "byd tyre";
	}

	@Override
	public Car produceCar() {
		// TODO Auto-generated method stub
		return new BydCar(engine, turbine, tyre);
	}

}
