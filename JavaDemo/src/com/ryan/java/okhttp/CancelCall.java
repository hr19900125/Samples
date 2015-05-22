package com.ryan.java.okhttp;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class CancelCall {

	private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	private final OkHttpClient client = new OkHttpClient();
	
	public void run() {
		Request request = new Request.Builder().url("http://httpbin.org/delay/2").build();
		
		final long startNanos = System.nanoTime();
		final Call call = client.newCall(request);
		
		executor.schedule(new Runnable() {
			
			@Override
			public void run() {
				System.out.printf("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
				call.cancel();
				System.out.printf("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
			}
		}, 1, TimeUnit.SECONDS);
		
		try {
		    System.out.printf("%.2f Executing call.%n", (System.nanoTime() - startNanos) / 1e9f);
		    Response response = call.execute();
		    System.out.printf("%.2f Call was expected to fail, but completed: %s%n", (System.nanoTime() - startNanos) / 1e9f, response);
		} catch (IOException e) {
			System.out.printf("%.2f Call failed as expected: %s%n", (System.nanoTime() - startNanos) / 1e9f, e);
		}
		
	}
	
	public static void main(String[] args) {
		new CancelCall().run();
	}
}
