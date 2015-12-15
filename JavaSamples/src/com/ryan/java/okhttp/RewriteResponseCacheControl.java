package com.ryan.java.okhttp;

import java.io.File;
import java.io.IOException;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Interceptor.Chain;

public class RewriteResponseCacheControl {

	private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
		
		@Override
		public Response intercept(Chain chain) throws IOException {
			Response originalResponse = chain.proceed(chain.request());
			return originalResponse.newBuilder().header("Cache-Control", "max-age=60").build();
		}
	};
	
	private final OkHttpClient client;
	
	public RewriteResponseCacheControl(File cacheDirectory) throws IOException {
		Cache cache = new Cache(cacheDirectory, 1024 * 1024);
		cache.evictAll();
		
		client = new OkHttpClient();
		client.setCache(cache);
	}
	
	public void run() throws IOException {
		for (int i = 0; i < 5; i ++) {
			System.out.println("    Request: " + i);
			
			Request request = new Request.Builder().url("https://api.github.com/search/repositories?q=http").build();
			
			if (i == 2) {
				System.out.println("Force cache: true");
				client.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
			} else {
				System.out.println("Force cache: false");
				client.networkInterceptors().clear();
			}
			
			Response response = client.newCall(request).execute();
			response.body().close();
			if(!response.isSuccessful()) throw new IOException("Unexpected code " + response);
			
			System.out.println("    Network: " + (response.networkResponse() != null));
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException {
		new RewriteResponseCacheControl(new File("RewriteResponseCacheControl.tmp")).run();
	}
	
}
