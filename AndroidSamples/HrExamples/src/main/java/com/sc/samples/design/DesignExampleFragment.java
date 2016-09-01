package com.sc.samples.design;

import android.content.Intent;

import com.sc.samples.BaseExampleFragment;
import com.sc.samples.design.mvp.MvpMainActivity;

/**
 *
 */
public class DesignExampleFragment extends BaseExampleFragment {

    @Override
    protected String[] initData() {
        return new String[]{"MVP Main", "MVP Login"};
    }

    @Override
    protected void handleClick(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(getActivity(), MvpMainActivity.class);
                break;
            case 1:
                break;
        }
        if (intent != null) {
            getActivity().startActivity(intent);
        }
    }
}
