
package com.ryan.java.http;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.security.KeyStore;

public class HttpUtils {

    private static HttpParams httpParams;
    private static ClientConnectionManager connectionManager;

    /**
     * 最大连接数
     */
    public final static int MAX_TOTAL_CONNECTIONS = 100;

    /**
     * 获取连接的最大等待时间
     */
    public final static int WAIT_TIMEOUT = 5000;

    /**
     * 每个路由的最大连接数
     */
    public final static int MAX_ROUTE_CONNECTIONS = 100;

    /**
     * 连接超时时间
     */
    public final static int CONNECT_TIMEOUT = 30000;

    /**
     * 读取超时时间
     */
    public final static int READ_TIMEOUT = 30000;

    public static HttpClient getNewHttpClient() {

        if(connectionManager == null){
            try {
                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);

                SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

                httpParams = new BasicHttpParams();
                // 设置最大连接数
                ConnManagerParams.setMaxTotalConnections(httpParams, MAX_TOTAL_CONNECTIONS);
                // 设置获取连接的最大等待时间
                ConnManagerParams.setTimeout(httpParams, WAIT_TIMEOUT);
                // 设置每个路由最大连接数
                ConnPerRouteBean connPerRoute = new ConnPerRouteBean(MAX_ROUTE_CONNECTIONS);
                ConnManagerParams.setMaxConnectionsPerRoute(httpParams, connPerRoute);
                // 设置连接超时时间
                HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);
                // 设置读取超时时间
                HttpConnectionParams.setSoTimeout(httpParams, READ_TIMEOUT);

                HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
                HttpProtocolParams.setContentCharset(httpParams, HTTP.DEFAULT_CONTENT_CHARSET);

                SchemeRegistry registry = new SchemeRegistry();
                registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
                registry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

                connectionManager = new ThreadSafeClientConnManager(httpParams, registry);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(connectionManager != null){
                    return new DefaultHttpClient(connectionManager, httpParams);
                }else{
                    return new DefaultHttpClient();
                }
            }
        }else{
            return new DefaultHttpClient(connectionManager, httpParams);
        }
    }

    public static HttpClient getNewLongHttpClient(){
        HttpClient httpClient = getNewHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIMEOUT);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 0);
        return httpClient;
    }

}
