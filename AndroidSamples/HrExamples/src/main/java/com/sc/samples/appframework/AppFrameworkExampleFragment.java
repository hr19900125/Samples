package com.sc.samples.appframework;

import android.content.Intent;

import com.sc.samples.BaseExampleFragment;

/**
 *
 */
public class AppFrameworkExampleFragment extends BaseExampleFragment {

    @Override
    protected String[] initData() {
        return new String[]{"timber+logback"};
    }

    @Override
    protected void handleClick(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent();
                intent.setClass(getActivity(), LogFrameActivity.class);
                break;
        }
        if (intent != null) {
            getActivity().startActivity(intent);
        }
    }
}
