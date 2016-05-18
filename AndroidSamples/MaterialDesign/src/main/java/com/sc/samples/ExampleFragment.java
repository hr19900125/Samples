package com.sc.samples;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sc.samples.example.ActLifeCycleActivity;
import com.sc.samples.example.FragLifeCycleActivity;

/**
 *
 */
public class ExampleFragment extends BaseFragment{

    private View mFragmentLayout;
    private ListView mListView;
    private String[] mExampleArray = new String[] {
            "Activity生命周期",
            "Fragment生命周期",
            "Book"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mFragmentLayout == null) {
            mFragmentLayout = inflater.inflate(R.layout.fragment_example, container, false);
        }
        return mFragmentLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        mListView = (ListView) mFragmentLayout.findViewById(R.id.listview);
        ListAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mExampleArray);
        mListView.setAdapter(listAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
    }

    private void selectItem(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent();
                intent.setClass(getActivity(), ActLifeCycleActivity.class);
                break;
            case 1:
                intent = new Intent();
                intent.setClass(getActivity(), FragLifeCycleActivity.class);
                break;
            case 2:
                break;
        }
        if(intent != null) {
            getActivity().startActivity(intent);
        }
    }


}
