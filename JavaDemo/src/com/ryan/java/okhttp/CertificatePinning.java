package com.ryan.java.okhttp;

import java.io.IOException;
import java.security.cert.Certificate;

import com.squareup.okhttp.CertificatePinner;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class CertificatePinning {

	private final OkHttpClient client;
	
	public CertificatePinning () {
		client = new OkHttpClient();
		client.setCertificatePinner(
		        new CertificatePinner.Builder()
		            .add("publicobject.com", "sha1/DmxUShsZuNiqPQsX2Oi9uv2sCnw=")
		            .add("publicobject.com", "sha1/SXxoaOSEzPC6BgGmxAt/EAcsajw=")
		            .add("publicobject.com", "sha1/blhOM3W9V/bVQhsWAcLYwPU6n24=")
		            .add("publicobject.com", "sha1/T5x9IXmcrQ7YuQxXnxoCmeeQ84c=")
		            .build());
	}
	
	public void run() throws IOException {
		Request request = new Request.Builder().url("https://publicobject.com/robots.txt").build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
		
		for (Certificate certificate : response.handshake().peerCertificates()) {
			System.out.println(CertificatePinner.pin(certificate));
		}
	}
	
	public static void main(String[] args) throws IOException {
		new CertificatePinning().run();
	}
	
}
