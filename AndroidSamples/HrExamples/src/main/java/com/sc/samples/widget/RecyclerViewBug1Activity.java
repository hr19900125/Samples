package com.sc.samples.widget;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RecyclerViewBug1Activity extends Activity {

    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecyclerAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    Button search;
    List<String> searchDatas = new ArrayList<>();
    boolean isLoading;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mRecyclerAdapter.notifyItemRangeInserted(0, 10);
            isLoading = false;
        }
    };

    private Runnable searchTask = new Runnable() {
        @Override
        public void run() {
            int size = searchDatas.size();
            for (int i = 0; i < 10; i++) {
                searchDatas.add("这是第" + (size + i) + "个搜索");
            }
            mHandler.sendEmptyMessage(0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_bug1);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerAdapter = new MyRecyclerAdapter(searchDatas, R.layout.item_one_textview);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        search = (Button) findViewById(R.id.button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLoading) {
                    isLoading = true;
                    searchDatas.clear();
                    mRecyclerAdapter.notifyDataSetChanged();
                    new Thread(searchTask).start();
                }
            }
        });
    }

    private static class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

        private List<String> items;
        private int itemLayoutId;

        public MyRecyclerAdapter(List<String> list, int layoutId) {
            this.items = list;
            this.itemLayoutId = layoutId;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.textView.setText(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            private TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text);
            }
        }

    }

}
