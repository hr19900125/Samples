package com.ryan.java.dp.builder;

public class BmwCarBuilder implements CarBuilder{

    private String engine;
    private String turbine;
    private String tyre;
	
	@Override
	public void assembleEngine() {
		// TODO Auto-generated method stub
		engine = "bmw engine";
	}

	@Override
	public void assembleTurbine() {
		// TODO Auto-generated method stub
		turbine = "bmw turbine";
	}

	@Override
	public void assembleTyre() {
		// TODO Auto-generated method stub
		tyre = "bmw tyre";
	}

	@Override
	public Car produceCar() {
		// TODO Auto-generated method stub
		return new BmwCar(engine, turbine, tyre);
	}

}
