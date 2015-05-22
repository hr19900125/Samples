package com.ryan.java.okhttp;

import java.io.IOException;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class PostForm {

	private final OkHttpClient client = new OkHttpClient();
	
	public void run() throws IOException {
		RequestBody formBody = new FormEncodingBuilder().add("search", "Jurassic Park").build();
		Request request = new Request.Builder().url("https://en.wikipedia.org/w/index.php").post(formBody).build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println(response.body().string());
	}
	
	public static void main(String[] args) throws IOException {
		new PostForm().run();
	}
	
}
