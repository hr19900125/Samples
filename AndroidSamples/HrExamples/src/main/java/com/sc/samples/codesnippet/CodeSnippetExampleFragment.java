package com.sc.samples.codesnippet;

import android.content.Intent;

import com.sc.samples.BaseExampleFragment;

/**
 *
 */
public class CodeSnippetExampleFragment extends BaseExampleFragment {

    @Override
    protected String[] initData() {
        return new String[]{"FileObserver", "获取应用签名(Apk Sign)", "SQLite SDCard", "监听网络状态变化", "运行一次就关闭的线程池"};
    }

    @Override
    protected void handleClick(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent();
                intent.setClass(getActivity(), FileObserverActivity.class);
                break;
            case 1:
                intent = new Intent();
                intent.setClass(getActivity(), ApkSignaturesActivity.class);
                break;
            case 2:
                intent = new Intent();
                intent.setClass(getActivity(), SQLiteSDCardActivity.class);
                break;
            case 3:
                intent = new Intent();
                intent.setClass(getActivity(), NetworkStateChangeActivity.class);
                break;
            case 4:
                intent = new Intent();
                intent.setClass(getActivity(), OnceThreadPoolActivity.class);
                break;
        }

        if (intent != null) {
            getActivity().startActivity(intent);
        }
    }
}
