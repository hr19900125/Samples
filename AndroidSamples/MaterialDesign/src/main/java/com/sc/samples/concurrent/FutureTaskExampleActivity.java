package com.sc.samples.concurrent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.BaseActivity;
import com.sc.samples.R;

/**
 * http://uule.iteye.com/blog/1539084
 * FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。
 * <p/>
 * 单独使用Runnable时：
 * 无法获得返回值
 * <p/>
 * 单独使用Callable时：
 * 无法在新线程中(new Thread(Runnable r))使用，只能使用ExecutorService
 * Thread类只支持Runnable
 * <p/>
 * FutureTask：
 * 实现了Runnable和Future，所以兼顾两者优点
 * 既可以使用ExecutorService，也可以使用Thread
 */
public class FutureTaskExampleActivity extends BaseActivity {

    private Button mButton;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_btn_and_textview);
        initView();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.btn);
        mResultTextView = (TextView) findViewById(R.id.textview);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    private void test() {

    }


}
