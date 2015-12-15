package com.ryan.java.okhttp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class PerCallSettings {

	private final OkHttpClient client = new OkHttpClient();
	
	public void run() {
		Request request = new Request.Builder().url("http://httpbin.org/delay/1").build();
		
		try {
			OkHttpClient cloned = client.clone();
			cloned.setReadTimeout(500, TimeUnit.MILLISECONDS);
			
			Response response = cloned.newCall(request).execute();
			System.out.println("Response 1 succeeded: " + response);
		} catch (IOException e) {
			System.out.println("Response 1 failed: " + e);
		}
		
		try {
			OkHttpClient cloned = client.clone();
			cloned.setReadTimeout(3000, TimeUnit.MILLISECONDS);

			Response response = cloned.newCall(request).execute();
			System.out.println("Response 2 succeeded: " + response);
		} catch (IOException e) {
			System.out.println("Response 2 failed: " + e);
		}
	}
	
    public static void main(String[] args) {
		new PerCallSettings().run();
	}
}
