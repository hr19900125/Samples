package com.sc.samples.example;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.sc.samples.R;

/**
 * Android应用AsyncTask处理机制详解及源码分析
 * http://blog.csdn.net/yanbober/article/details/46117397
 */
public class AsyncTaskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchevent_dispatch_1);

        new TestAsyncTask(this).execute();
    }

    //如下三个泛型参数从左到右含义依次为：
    //1. 在执行AsyncTask时需要传入的参数，可用于在后台任务中使用。
    //2. 后台任务执行时，如果需要在界面上显示当前的进度，则使用这个。
    //3. 当任务执行完毕后，如果需要对结果进行返回，则使用这个。
    final static class TestAsyncTask extends AsyncTask<Void, Integer, Boolean> {

        private Context mContext;
        private ProgressDialog mDialog;
        private int mCount;

        public TestAsyncTask(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog = new ProgressDialog(mContext);
            mDialog.setMax(100);
            mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            while (mCount < 100) {
                publishProgress(mCount);
                mCount += 20;
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean && mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
        }
    }
}
