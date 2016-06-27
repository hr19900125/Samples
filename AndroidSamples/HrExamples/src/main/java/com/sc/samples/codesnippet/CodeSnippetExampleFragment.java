package com.sc.samples.codesnippet;

import android.content.Intent;

import com.sc.samples.BaseExampleFragment;

/**
 *
 */
public class CodeSnippetExampleFragment extends BaseExampleFragment {

    @Override
    protected String[] initData() {
        return new String[]{"FileObserver"};
    }

    @Override
    protected void handleClick(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent();
                intent.setClass(getActivity(), FileObserverActivity.class);
                break;
        }

        if (intent != null) {
            getActivity().startActivity(intent);
        }
    }
}
