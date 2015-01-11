package com.ryan.java.dp.chain;

public abstract class AbstractHandler {

	protected Handler mHandler;
	
	public void setHandler(Handler handler){
		this.mHandler = handler;
	}
	
	public Handler getHandler(){
		return this.mHandler;
	}

}
