package com.example.huangrui.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewActivity extends Activity {

    private static final String LOG_TAG = "RecyclerViewActivity";

    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final List<ViewModel> itemList = initTestData();
        mRecyclerAdapter = new MyRecyclerAdapter(itemList, R.layout.item);
        mRecyclerAdapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i(LOG_TAG, "onItemClick Item info : "+itemList.get(position).name);
            }
        });
        mRecyclerAdapter.setOnItemLongClickListener(new MyItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int position) {
                Log.i(LOG_TAG, "onItemLongClick Item info : "+itemList.get(position).name);
                mRecyclerAdapter.remove(position);
            }
        });
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<ViewModel> initTestData(){
        List<ViewModel> list = new ArrayList<>();
        ViewModel model = null;
        for (int i=0;i < 20; i ++) {
            model = new ViewModel();
            model.name = "Text Item No " + (i+1);
            list.add(model);
        }
        return list;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
