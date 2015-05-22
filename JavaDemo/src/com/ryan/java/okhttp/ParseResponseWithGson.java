package com.ryan.java.okhttp;

import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class ParseResponseWithGson {

	private final OkHttpClient client = new OkHttpClient();
	private final Gson gson = new Gson();
	
	public void run() throws IOException {
		Request request = new Request.Builder().url("https://api.github.com/gists/c2a7c39532239ff261be").build();
	    Response response = client.newCall(request).execute();
	    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
	    
	    Gist gist = gson.fromJson(response.body().charStream(), Gist.class);
	    for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
	    	System.out.println(entry.getKey());
	    	System.out.println(entry.getValue().content);
	    }
	}
	
	static class Gist {
		Map<String, GistFile> files;
	}
	
	static class GistFile {
		String content;
	}
	
	public static void main(String[] args) throws IOException {
		new ParseResponseWithGson().run();
	}
	
}
