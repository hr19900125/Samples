package com.sc.samples.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sc.samples.R;

/**
 * Activity 生命周期
 */
public class ActLifeCycleActivity extends Activity {

    private static final String LOG_TAG = "ActivityLifeCycle";
    private String mStr = "I Can";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreate called");
        setContentView(R.layout.activity_act_life_cycle);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActLifeCycleActivity.this, ActLifeCycle2Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "onRestart called");
    }

    //Activity 创建或者从后台重新回到前台时被调用
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart called");
    }

    //Activity 创建或者从被覆盖、后台重新回到前台时被调用
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume called");
    }

    //Activity被覆盖到下面或者锁屏时被调用
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause called");
    }

    //退出当前Activity或者跳转到新Activity时被调用
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop called");
    }

    //退出当前Activity时被调用,调用之后Activity就结束了
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy called");
    }

    /**
     * Activity被系统杀死时被调用.
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死.
     * 另外,当跳转到其他Activity或者按Home键回到主屏时该方法也会被调用,系统是为了保存当前View组件的状态.
     * 在onStop之前被调用.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("param", mStr);
        Log.i(LOG_TAG, "onSaveInstanceState called. put param: " + mStr);
        super.onSaveInstanceState(outState);
    }

    /**
     * Activity被系统杀死后再重建时被调用.
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死,用户又启动该Activity.
     * 这两种情况下onRestoreInstanceState都会被调用,在onStart之后.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mStr = savedInstanceState.getString("param");
        Log.i(LOG_TAG, "onRestoreInstanceState called. get param: " + mStr);
        super.onRestoreInstanceState(savedInstanceState);
    }

}
