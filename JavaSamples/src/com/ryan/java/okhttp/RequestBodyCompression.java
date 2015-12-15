package com.ryan.java.okhttp;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

import com.google.gson.Gson;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class RequestBodyCompression {

	public static final String GOOGLE_API_KEY = "AIzaSyAx2WZYe0My0i-uGurpvraYJxO7XNbwiGs";
	public static final MediaType MEDIA_TYPE_JSON = MediaType
			.parse("application/json");
	
	private final OkHttpClient client = new OkHttpClient();
	
	public RequestBodyCompression() {
		client.interceptors().add(new GzipRequestInterceptor());
	}
	
	public void run() throws IOException {
		Map<String, String> requestBody = new LinkedHashMap<String, String>();
		requestBody.put("longUrl", "https://publicobject.com/2014/12/04/html-formatting-javadocs/");
		RequestBody jsonRequestBody = RequestBody.create(MEDIA_TYPE_JSON, new Gson().toJson(requestBody));
		
		Request request = new Request.Builder().url("https://www.googleapis.com/urlshortener/v1/url?key=" + GOOGLE_API_KEY)
				          .post(jsonRequestBody).build();
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println(response.body().string());
		
	}
	
	static class GzipRequestInterceptor implements Interceptor {

		@Override
		public Response intercept(Chain chain) throws IOException {
			Request originalRequest = chain.request();
			if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
				return chain.proceed(originalRequest);
			}
			
			Request compressedRequest = originalRequest.newBuilder().header("Content-Encoding", "gzip")
					                    .method(originalRequest.method(), gzip(originalRequest.body()))
					                    .build();
			
			return chain.proceed(compressedRequest);
		}
		
		private RequestBody gzip(final RequestBody body) {
			return new RequestBody() {
				
				@Override
				public void writeTo(BufferedSink sink) throws IOException {
					BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
					body.writeTo(gzipSink);
					gzipSink.close();
				}
				
				@Override
				public MediaType contentType() {
					return body.contentType();
				}
				
				@Override
				public long contentLength() throws IOException {
					return -1;
				}
			};
		}
		
	}
	
}
