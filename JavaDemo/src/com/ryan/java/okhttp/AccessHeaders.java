package com.ryan.java.okhttp;

import java.io.IOException;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class AccessHeaders {
	
	 private final OkHttpClient client = new OkHttpClient();
	 
	 public void run() throws IOException{
		 Request request = new Request.Builder().url("https://api.github.com/repos/square/okhttp/issues")
				           .header("User-Agent", "OkHttp Headers.java")
				           .addHeader("Accept", "application/json; q=0.5")
				           .addHeader("Accept", "application/vnd.github.v3+json")
				           .build();
		 
		 Response response = client.newCall(request).execute();
		 if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		 
		 System.out.println("Server: " + response.header("Server"));
		 System.out.println("Date: " + response.header("Date"));
		 System.out.println("Vary: " + response.header("Vary"));
	
	 }
	 
	 public static void main(String[] args) throws IOException {
		new AccessHeaders().run();
	}

}
