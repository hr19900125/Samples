package com.ryan.java.okhttp;

import java.io.IOException;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class AsynchronousGet {

	private final OkHttpClient client = new OkHttpClient();
	
	public void run(){
		Request request = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();
		
		client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Response response) throws IOException {
				if(!response.isSuccessful()) throw new IOException("Unexpected code " + response);
				
				Headers responseHeaders = response.headers();
				for (int i = 0, size = responseHeaders.size(); i < size;i ++) {
					System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
				}
				
				System.out.println(response.body().string());
			}
			
			@Override
			public void onFailure(Request request, IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	public static void main(String... args){
		new AsynchronousGet().run();
	}
	
}
