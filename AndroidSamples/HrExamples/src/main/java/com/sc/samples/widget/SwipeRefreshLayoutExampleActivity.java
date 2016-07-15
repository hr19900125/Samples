package com.sc.samples.widget;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.sc.samples.R;

/**
 * swiperefreshlayout Example
 * https://yanlu.me/android5-0-swiperefreshlayout/
 */
public class SwipeRefreshLayoutExampleActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout_example);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        //设置触发刷新时的回调方法
        mSwipeRefreshLayout.setOnRefreshListener(this);
//        设置加载圈的背景颜色
//        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource();
        //设置加载圈圈的颜色 一直循环，最多四种
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //设置正在刷新的圈圈停止刷新
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }
}
