package com.ryan.java.okhttp;


import java.io.IOException;

import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

public class Progress {

	private final OkHttpClient client = new OkHttpClient();
	
	public void run() throws IOException {
		Request request = new Request.Builder().url("https://publicobject.com/helloworld.txt").build();
		
		final ProgressListener progressListener = new ProgressListener() {
			
			@Override
			public void update(long bytesRead, long contentLength, boolean done) {
                System.out.println(bytesRead);
                System.out.println(contentLength);
                System.out.println(done);
                System.out.format("%d%% done\n", (100 * bytesRead) / contentLength);
			}
		};
		
		client.networkInterceptors().add(new Interceptor() {
			
			@Override
			public Response intercept(Chain chain) throws IOException {
				Response originalResponse = chain.proceed(chain.request());
				return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(), progressListener)).build();
			}
		});
		
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println(response.body().string());
	}
	
	public static void main(String[] args) throws IOException {
		new Progress().run();
	}
	
	private static class ProgressResponseBody extends ResponseBody {

		private final ResponseBody responseBody;
		private final ProgressListener progressListener;
		private BufferedSource bufferedSource;
		
		public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
			this.responseBody = responseBody;
			this.progressListener = progressListener;
		}
		
		@Override
		public long contentLength() throws IOException {
			return responseBody.contentLength();
		}

		@Override
		public MediaType contentType() {
			return responseBody.contentType();
		}

		@Override
		public BufferedSource source() throws IOException {
			if (bufferedSource == null) {
				bufferedSource = Okio.buffer(source(responseBody.source()));
			}
			return bufferedSource;
		}
		
		private okio.Source source(okio.Source source) {
			
			return new ForwardingSource(source) {

				long totalBytesRead = 0;
				
				@Override
				public long read(Buffer sink, long byteCount) throws IOException {
					long bytesRead = super.read(sink, byteCount);
					totalBytesRead += bytesRead != -1 ? bytesRead : 0;
					progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
					return bytesRead;
				}
				
			};
		}
		
		
	}
	
	interface ProgressListener {
		void update(long bytesRead, long contentLength, boolean done);
	}
	
}
