package com.ryan.java.okhttp;

import java.io.File;
import java.io.IOException;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class CacheResponse {

	private final OkHttpClient client;
	
	public CacheResponse(File cacheDirectory) {
		int cacheSize = 10 * 1024 * 1024;
		Cache cache = new Cache(cacheDirectory, cacheSize);
		
		client = new OkHttpClient();
		client.setCache(cache);
	}
	
	public void run() throws IOException {
		Request request = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();
		
		Response response1 = client.newCall(request).execute();
		if (!response1.isSuccessful()) throw new IOException("Unexpected code " + response1);
		
		String response1Body = response1.body().string();
		System.out.println("Response 1 response:         " + response1);
		System.out.println("Response 1 cache response:   " + response1.cacheResponse());
		System.out.println("Response 1 network response: " + response1.networkResponse());
		
		Response response2 = client.newCall(request).execute();
		if (!response2.isSuccessful()) throw new IOException("Unexpected code " + response2);
		
		String response2Body = response2.body().string();
		System.out.println("Response 2 response:         " + response2);
		System.out.println("Response 2 cache response:   " + response2.cacheResponse());
		System.out.println("Response 2 network response: " + response2.networkResponse());
	}
	
	public static void main(String[] args) throws IOException {
		new CacheResponse(new File("CacheResponse.tmp")).run();
	}
	
}
