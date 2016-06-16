package com.sc.samples;

import android.content.Intent;

/**
 * 设计模式
 */
public class DesignPatternExampleFragment extends BaseExampleFragment{

    @Override
    protected String[] initData() {
        return new String[]{"Design Pattern (1)"};
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
