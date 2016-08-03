package com.sc.samples.realm;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.sc.samples.BaseExampleFragment;

/**
 * Realm Examples
 */
public class RealmExampleFragment extends BaseExampleFragment {

    @Override
    protected String[] initData() {
        return new String[]{
                "Hello Realm"
        };
    }

    @Override
    protected void handleClick(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent();
                intent.setClass(getActivity(), RealmHelloWorldActivity.class);
                break;
        }
        if (intent != null) {
            getActivity().startActivity(intent);
        }
    }
}
