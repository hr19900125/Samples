package com.ryan.java.okhttp;

import java.io.File;
import java.io.IOException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class PostFile {

	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
	
	private final OkHttpClient client = new OkHttpClient();
	
	public void run() throws IOException {
		File file = new File("README.md");
		
		Request request = new Request.Builder().url("https://api.github.com/markdown/raw")
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file)).build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println(response.body().string());
	}
	
	public static void main(String[] args) throws IOException {
		new PostFile().run();
	}
	
}
