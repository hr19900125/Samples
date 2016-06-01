package com.sc.samples;

import android.content.Intent;

/**
 * Java 并发
 */
public class JavaConcurrentExampleFragment extends BaseExampleFragment {

    @Override
    protected String[] initData() {
        return new String[]{"Java Concurrent : Callable"};
    }

    @Override
    protected void handleClick(int position) {
        Intent intent = null;
        switch (position) {
            default:
        }

        if(intent != null) {
            getActivity().startActivity(intent);
        }
    }
}
