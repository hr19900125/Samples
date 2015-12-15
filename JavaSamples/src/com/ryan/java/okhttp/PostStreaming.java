package com.ryan.java.okhttp;

import java.io.IOException;

import okio.BufferedSink;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class PostStreaming {

	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
	
	private final OkHttpClient client = new OkHttpClient();
	
	public void run() throws IOException {
		RequestBody requestBody = new RequestBody() {
			
			@Override
			public void writeTo(BufferedSink sink) throws IOException {
				sink.writeUtf8("Numbers\n");
				sink.writeUtf8("-------\n");
				for (int i = 2; i <= 997; i ++) {
					sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
				}
			}
			
			@Override
			public MediaType contentType() {
				return MEDIA_TYPE_MARKDOWN;
			}
			
			private String factor(int n) {
				for (int i = 2; i < n; i ++) {
					int x = n / i;
					if (x * i == n) return factor(x) + " x " + i;
				}
				return Integer.toString(n);
			}
		};
		
		Request request = new Request.Builder().url("https://api.github.com/markdown/raw").post(requestBody).build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println(response.body().string());
		
	}
	
	public static void main(String[] args) throws IOException {
		new PostStreaming().run();
	}
	
}
