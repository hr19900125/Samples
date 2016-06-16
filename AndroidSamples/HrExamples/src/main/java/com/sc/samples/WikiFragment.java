package com.sc.samples;

import android.content.Intent;

import com.sc.samples.wiki.ArticleActivity;

/**
 * 收集一些好的网上的文章，组织成wiki
 */
public class WikiFragment extends BaseExampleFragment {

    @Override
    protected String[] initData() {
        return new String[]{"EventLog日志分析", "dumpsys命令用法", "RxJava操作符：defer"};
    }

    @Override
    protected void handleClick(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent();
                //EventLog 日志分析
                intent.putExtra(ArticleActivity.EXTRA_URL, "http://gityuan.com/2016/05/15/event-log/");
                intent.setClass(getActivity(), ArticleActivity.class);
                break;
            case 1:
                intent = new Intent();
                //dumpsys命令用法
                intent.putExtra(ArticleActivity.EXTRA_URL, "http://gityuan.com/2016/05/15/event-log/");
                intent.setClass(getActivity(), ArticleActivity.class);
                break;
            case 2:
                intent = new Intent();
                //RxJava操作符号：defer
                intent.putExtra(ArticleActivity.EXTRA_URL, "http://www.jianshu.com/p/c83996149f5b");
                intent.setClass(getActivity(), ArticleActivity.class);
                break;
        }
        if (intent != null) {
            getActivity().startActivity(intent);
        }
    }
}
