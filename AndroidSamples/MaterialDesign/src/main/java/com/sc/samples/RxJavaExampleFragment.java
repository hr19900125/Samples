package com.sc.samples;

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

import com.sc.samples.rxjava.HelloWorldActivity;
import com.sc.samples.rxjava.ObservableCreateActivity;
import com.sc.samples.rxjava.ObservableJustActivity;

/**
 *
 */
public class RxJavaExampleFragment extends BaseFragment {

    private View mFragmentView;
    private ListView mListView;
    private String[] mData = new String[]{
            "RxJava :Hello world",
            "RxJava :Creating an Observable via create",
            "RxJava :creating an Observable via just"
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
                intent.setClass(getActivity(), HelloWorldActivity.class);
                break;
            case 1:
                intent = new Intent();
                intent.setClass(getActivity(), ObservableCreateActivity.class);
                break;
            case 2:
                intent = new Intent();
                intent.setClass(getActivity(), ObservableJustActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
