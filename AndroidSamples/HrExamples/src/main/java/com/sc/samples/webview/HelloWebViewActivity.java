package com.sc.samples.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sc.samples.R;

/**
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2013/1010/1569.html
 */
public class HelloWebViewActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_webview);
        mWebView = (WebView) findViewById(R.id.webview);
        settingWebView(mWebView);
        mWebView.loadUrl("http://www.baidu.com");
    }

    private void settingWebView(WebView webView) {
        /**
         * 表示不支持js，如果想让java和js交互或者本身希望js完成一定的功能请把false改为true
         */
        webView.getSettings().setJavaScriptEnabled(false);

        /**
         * 设置是否支持缩放，我这里为false，默认为true
         */
        webView.getSettings().setSupportZoom(false);

        /**
         * 设置是否显示缩放工具，默认为false
         */
        webView.getSettings().setBuiltInZoomControls(false);

        /**
         * 一般很少会用到这个，用WebView组件显示普通网页时一般会出现横向滚动条，这样会导致页面查看起来非常不方便
         * LayoutAlgorithm是一个枚举，用来控制html的布局，总共有三种类型：
         * NORMAL：正常显示，没有渲染变化。
         * SINGLE_COLUMN：把所有内容放到WebView组件等宽的一列中。
         * NARROW_COLUMNS：可能的话，使所有列的宽度不超过屏幕宽度。
         */
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        /**
         * 设置默认的字体大小，默认为16，有效值区间在1-72之间
         */
        webView.getSettings().setDefaultFontSize(18);

        /**
         * 设置WebViewClient，避免使用系统默认的浏览器打开链接
         */
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return true;
            }

            /**
             * 监听错误码
             */
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Log.e("HelloWebView", "errorCode = " + error);
            }
        });

        /**
         * 可以设置WebChromeClient监听一些信息
         */
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
                Log.e("HelloWebView", "progress : " + newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
                Log.e("HelloWebView", "title : " + title);
            }
        });

        /**
         * 设置DownloadListener可以监听文件需要下载的文件链接
         */
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

            }
        });

    }
}
