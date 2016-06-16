package com.sc.samples.wiki;

import android.app.Activity;
import android.os.Bundle;

import com.sc.samples.R;

/**
 *
 */
public class ArticleActivity extends Activity {

    public static final String EXTRA_URL = "extra_url";
    private ProgressWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        mWebView = (ProgressWebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        String url = getIntent().getStringExtra(EXTRA_URL);
        mWebView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }
}
