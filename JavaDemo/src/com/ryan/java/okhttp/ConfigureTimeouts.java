package com.ryan.java.okhttp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class ConfigureTimeouts {

	private final OkHttpClient client;
	
	public ConfigureTimeouts() {
		client = new OkHttpClient();
		client.setConnectTimeout(10, TimeUnit.SECONDS);
		client.setWriteTimeout(10, TimeUnit.SECONDS);
		client.setReadTimeout(30, TimeUnit.SECONDS);
	}
	
	public void run() throws IOException {
		Request request = new Request.Builder().url("http://httpbin.org/delay/2").build();
		
		Response response = client.newCall(request).execute();
		System.out.println("Response completed: " + response);
	}
	
	public static void main(String[] args) throws IOException {
		new ConfigureTimeouts().run();
	}
	
}
