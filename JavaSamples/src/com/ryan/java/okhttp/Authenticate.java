package com.ryan.java.okhttp;

import java.io.IOException;
import java.net.Proxy;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class Authenticate {

	private final OkHttpClient client = new OkHttpClient();
	
	public void run() throws IOException {
		client.setAuthenticator(new Authenticator() {
			
			@Override
			public Request authenticateProxy(Proxy proxy, Response response)
					throws IOException {
				return null;
			}
			
			@Override
			public Request authenticate(Proxy proxy, Response response) throws IOException {
				System.out.println("Authenticating for response: " + response);
				System.out.println("Challenges: " + response.challenges());
				String credential = Credentials.basic("jesse", "password1");
				return response.request().newBuilder().header("Authorization", credential).build();
			}
		});
		
		Request request = new Request.Builder().url("http://publicobject.com/secrets/hellosecret.txt").build();
		
		Response response = client.newCall(request).execute();
		if(!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println(response.body().string());
	}
	
	public static void main(String[] args) throws IOException {
		new Authenticate().run();
	}
	
}
