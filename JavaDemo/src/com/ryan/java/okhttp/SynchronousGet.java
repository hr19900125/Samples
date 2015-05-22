package com.ryan.java.okhttp;

import java.io.IOException;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class SynchronousGet {

	private final OkHttpClient client = new OkHttpClient();
	
	public void run() throws IOException {
		Request request = new Request.Builder().url("https://publicobject.com/helloworld.txt").build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		Headers responseHeaders = response.headers();
		for (int i=0; i < responseHeaders.size(); i ++) {
			System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
		}
	}
	
	public static void main(String[] args) throws IOException {
		new SynchronousGet().run();
	}
	
}
