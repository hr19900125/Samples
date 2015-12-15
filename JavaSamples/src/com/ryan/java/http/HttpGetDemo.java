package com.ryan.java.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;



public class HttpGetDemo {

    public static void main(String[] args) {
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpGet httpGet = new HttpGet("http://www.baidu.com");
            System.out.println("executing request " + httpGet.getURI());
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            System.out.println("--------------------------------------");
            System.out.println(response.getStatusLine());
            if(entity != null){
                System.out.println("Response Content Length:"+entity.getContentLength());
                System.out.println("Response Content Encoding:"+entity.getContentEncoding());
                System.out.println("Response Content Type:"+entity.getContentType());
                System.out.println("Response Content:"+EntityUtils.toString(entity));
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            httpClient.getConnectionManager().shutdown();
        }
        
      
    }
}
