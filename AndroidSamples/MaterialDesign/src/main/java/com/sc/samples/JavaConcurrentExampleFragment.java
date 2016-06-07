package com.sc.samples;

import android.content.Intent;

import com.sc.samples.concurrent.BlockingQueueExampleActivity;
import com.sc.samples.concurrent.CallableAndFutureExampleActivity;
import com.sc.samples.concurrent.ExecutorServiceExampleActivity;

/**
 * Java 并发
 */
public class JavaConcurrentExampleFragment extends BaseExampleFragment {

    @Override
    protected String[] initData() {
        return new String[]{"Java Concurrent : ExecutorService (SingleThreadExecutor)", "Java Concurrent : BlockingQueue", "Java Concurrent : Callable and Future"};
    }

    @Override
    protected void handleClick(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent();
                intent.setClass(getActivity(), ExecutorServiceExampleActivity.class);
                break;
            case 1:
                intent = new Intent();
                intent.setClass(getActivity(), BlockingQueueExampleActivity.class);
                break;
            case 2:
                intent = new Intent();
                intent.setClass(getActivity(), CallableAndFutureExampleActivity.class);
                break;
        }

        if (intent != null) {
            getActivity().startActivity(intent);
        }
    }
}
