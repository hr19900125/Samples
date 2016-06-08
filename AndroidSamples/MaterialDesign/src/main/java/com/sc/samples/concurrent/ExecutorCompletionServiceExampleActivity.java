package com.sc.samples.concurrent;

import android.os.Bundle;

import com.sc.samples.BaseActivity;
import com.sc.samples.R;

/**
 * ExecutorCompletionService Example
 * http://www.jianshu.com/p/cfda708a3478
 *
 * ExecutorCompletionService主要用与管理异步任务 (有结果的任务, 任务完成后要处理结果)
 */
public class ExecutorCompletionServiceExampleActivity extends BaseActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_btn_and_textview);
    }
}
