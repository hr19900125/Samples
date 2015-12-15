package com.example.huangrui.jobqueue;

import android.util.Log;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

/**
 *
 */
public class PrintJob extends Job{

    private int id;
    private String msg;

    private static final String LOG_TAG = "PrintJob";

    private static void logI(String str){
        Log.i(LOG_TAG, str);
    }

    public PrintJob(Params params, int id, String msg){
        super(params);
        this.id = id;
        this.msg = msg;
    }

    @Override
    public void onAdded() {
        logI("PrintJob["+id+"] onAdded -- Thread["+Thread.currentThread()+"] ");
    }

    @Override
    public void onRun() throws Throwable {
        logI("PrintJob["+id+"] onRun Begin -- Thread["+Thread.currentThread()+"]");
        Thread.sleep(30000);
        logI("PrintJob["+id+"] onRun End -- Thread["+Thread.currentThread()+"]");
    }

    @Override
    protected void onCancel() {
        logI("PrintJob["+id+"] onCancel -- Thread["+Thread.currentThread()+"]");
    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}
