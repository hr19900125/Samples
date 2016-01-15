package com.ryan.sample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewActivity extends AppCompatActivity {

    private static final String TAG = "SurfaceViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MySurfaceView(this));
    }

    class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

        private SurfaceHolder holder;
        private MyThread mThread;

        public MySurfaceView(Context context) {
            super(context);
            holder = this.getHolder();
            holder.addCallback(this);
            mThread = new MyThread(holder);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.i(TAG,"surfaceCreated is called");

            mThread.isRun = true;
            mThread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.i(TAG,"surfaceChanged is called");
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.i(TAG,"surfaceDestroyed is called");

            mThread.isRun = false;
            mThread.stop();
        }
    }

    class MyThread extends Thread {

        private SurfaceHolder holder;
        public boolean isRun = false;

        public MyThread(SurfaceHolder holder) {
            this.holder = holder;
            isRun = true;
            Log.i(TAG,"MyThread set surface holder");
        }

        @Override
        public void run() {
            Canvas canvas = null;
            int count = 0;
            while (isRun) {
                try {
                    synchronized (holder) {
                        canvas = holder.lockCanvas(); //锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
                        canvas.drawColor(Color.BLACK); //设置画布背景颜色
                        Paint paint = new Paint();
                        paint.setColor(Color.RED);
                        Rect rect = new Rect(500, 200, 300, 250);
                        canvas.drawRect(rect,paint);
                        canvas.drawText("这是第" + (count++) + "秒", 300, 310, paint);
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(canvas != null) {
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
}
