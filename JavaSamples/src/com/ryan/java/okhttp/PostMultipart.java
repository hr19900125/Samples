package com.ryan.java.okhttp;

import java.io.File;
import java.io.IOException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class PostMultipart {

	private static final String IMGUR_CLIENT_ID = "9199fdef135c122";
	private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
	  
	private final OkHttpClient client = new OkHttpClient();
	
	public void run() throws IOException {
		RequestBody requestBody = new MultipartBuilder().type(MultipartBuilder.FORM)
				                  .addFormDataPart("title", "Square Logo")
				                  .addFormDataPart("image", null, RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
				                  .build();
		
		Request request = new Request.Builder()
		                  .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
		                  .url("https://api.imgur.com/3/image")
		                  .post(requestBody)
		                  .build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println(response.body().string());
	}
	
	public static void main(String[] args) throws IOException {
		new PostMultipart().run();
	}
	
}
