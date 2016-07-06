package com.sc.samples.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sc.samples.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView的基本使用
 */
public class RecyclerViewExampleActivity extends AppCompatActivity {

    private static final String LOG_TAG = RecyclerViewExampleActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final List<ViewModel> itemList = initTestData();
        mRecyclerAdapter = new MyRecyclerAdapter(itemList, R.layout.item_one_textview);
        mRecyclerAdapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i(LOG_TAG, "onItemClick Item info : " + itemList.get(position).name);
            }
        });
        mRecyclerAdapter.setOnItemLongClickListener(new MyItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int position) {
                Log.i(LOG_TAG, "onItemLongClick Item info : " + itemList.get(position).name);
                mRecyclerAdapter.remove(position);
            }
        });
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<ViewModel> initTestData() {
        List<ViewModel> list = new ArrayList<>();
        ViewModel model = null;
        for (int i = 0; i < 20; i++) {
            model = new ViewModel();
            model.name = "Text Item No " + (i + 1);
            list.add(model);
        }
        return list;
    }

    private static class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

        private List<ViewModel> items;
        private int itemLayoutId;
        private MyItemClickListener mItemClickListener;
        private MyItemLongClickListener mItemLongClickListener;

        public MyRecyclerAdapter(List<ViewModel> list, int layoutId) {
            this.items = list;
            this.itemLayoutId = layoutId;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
            return new ViewHolder(v, mItemClickListener, mItemLongClickListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ViewModel item = items.get(position);
            holder.textView.setText(item.name);
            holder.itemView.setTag(item);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void setOnItemClickListener(MyItemClickListener listener) {
            this.mItemClickListener = listener;
        }

        public void setOnItemLongClickListener(MyItemLongClickListener listener) {
            this.mItemLongClickListener = listener;
        }

        public void add(ViewModel model, int position) {
            items.add(position, model);
            notifyItemInserted(position);
        }

        public void remove(int position) {
            items.remove(position);
            notifyItemRemoved(position);
        }

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            public TextView textView;
            private MyItemClickListener mOnItemClickListener;
            private MyItemLongClickListener mOnLongClickListener;

            public ViewHolder(View itemView, MyItemClickListener clickListener, MyItemLongClickListener longClickListener) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.text);
                this.mOnItemClickListener = clickListener;
                this.mOnLongClickListener = longClickListener;
                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, getPosition());
                }
            }

            @Override
            public boolean onLongClick(View v) {
                if (mOnLongClickListener != null) {
                    mOnLongClickListener.onItemLongClick(v, getPosition());
                }
                return true;
            }
        }

    }

    private interface MyItemLongClickListener {
        public void onItemLongClick(View v, int position);
    }

    private interface MyItemClickListener {
        public void onItemClick(View view, int position);
    }

    private static class ViewModel {

        public String name;

    }
}
