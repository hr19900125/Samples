package com.ryan.java.okhttp;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.Set;

import com.squareup.okhttp.CertificatePinner;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class CheckHandshake {

	private static final Interceptor CHECK_HANDSHAKE_INTERCEPTOR = new Interceptor(){

		Set<String> blacklist = Collections.singleton("sha1/DmxUShsZuNiqPQsX2Oi9uv2sCnw=");
		
		@Override
		public Response intercept(Chain chain) throws IOException {
			for (Certificate certificate : chain.connection().getHandshake().peerCertificates()) {
				String pin = CertificatePinner.pin(certificate);
				if(blacklist.contains(pin)) {
					throw new IOException("Blacklisted peer certificate: " + pin);
				}
			}
			return chain.proceed(chain.request());
		}
		
	};
	
	private final OkHttpClient client = new OkHttpClient();
	
	public CheckHandshake() {
		client.networkInterceptors().add(CHECK_HANDSHAKE_INTERCEPTOR);
	}
	
	public void run() throws IOException {
		Request request = new Request.Builder().url("https://publicobject.com/helloworld.txt").build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		System.out.println(response.body().string());
	}
	
	public static void main(String[] args) throws IOException {
		new CheckHandshake().run();
	}
	
}
