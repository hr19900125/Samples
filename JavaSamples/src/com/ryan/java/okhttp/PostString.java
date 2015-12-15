package com.ryan.java.okhttp;

import java.io.IOException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class PostString {

	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
	
	private final OkHttpClient client = new OkHttpClient();
	
	public void run() throws IOException {
		String postBody = ""
		    + "Releases\n"
			+ "--------\n"
		    + "\n"
			+ " * _1.0_ May 6, 2013\n"
		    + " * _1.1_ June 15, 2013\n"
			+ " * _1.2_ August 11, 2013\n";
		
		Request request = new Request.Builder().url("https://api.github.com/markdown/raw")
				          .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
				          .build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println(response.body().string());
	}
	
	public static void main(String[] args) throws IOException {
		new PostString().run();
	}
	
}
