package com.sc.samples.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sc.samples.BaseFragment;
import com.sc.samples.R;
import com.sc.samples.widget.custom.CustomViewActivity;
import com.sc.samples.widget.custom.CustomViewActivity2;

/**
 */
public class WidgetFragment extends BaseFragment {

    private View mFragmentView;
    private ListView mListView;
    private String[] mData = new String[]{
            "Android自定义View属性(attr)",
            "Android自定义View属性(declare-styleable)",
            "一个RecyclerView原生BUG的探讨",
            "RecyclerView Example",
            "RecyclerView Example(2)",
            "SwipeRefreshLayout Example",
            "StatusBarUtils(1)",
            "ViewPager Example"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(R.layout.fragment_example, container, false);
        }
        return mFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mListView = (ListView) mFragmentView.findViewById(R.id.listview);
        ListAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mData);
        mListView.setAdapter(listAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleClick(position);
            }
        });
    }

    private void handleClick(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent();
                intent.setClass(getActivity(), CustomViewActivity.class);
                break;
            case 1:
                intent = new Intent();
                intent.setClass(getActivity(), CustomViewActivity2.class);
                break;
            case 2:
                intent = new Intent();
                intent.setClass(getActivity(), RecyclerViewBug1Activity.class);
                break;
            case 3:
                intent = new Intent();
                intent.setClass(getActivity(), RecyclerViewExampleActivity.class);
                break;
            case 4:
                intent = new Intent();
                intent.setClass(getActivity(), RecyclerViewExample2Activity.class);
                break;
            case 5:
                intent = new Intent();
                intent.setClass(getActivity(), SwipeRefreshLayoutExampleActivity.class);
                break;
            case 6:
//                intent = new Intent();
//                intent.setClass(getActivity(), );
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

}
