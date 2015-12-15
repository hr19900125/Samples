package com.ryan.java.dp.chain;

public class MyHandler extends AbstractHandler implements Handler{

	private String name;
	
	public MyHandler(String name){
		this.name = name;
	}
	
	@Override
	public void operator() {
		System.out.println(name);
		if(mHandler != null){
			mHandler.operator();
		}
	}


}
