package com.ryan.http;

public class HttpResult {

	private int resultCode; //结果code
	private Object resultValue;//结果value
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public Object getResultValue() {
		return resultValue;
	}
	public void setResultValue(Object resultValue) {
		this.resultValue = resultValue;
	}
	
	
}