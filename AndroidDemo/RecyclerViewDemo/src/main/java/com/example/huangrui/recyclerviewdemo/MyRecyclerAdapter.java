package com.example.huangrui.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>  {

    private List<ViewModel> items;
    private int itemLayoutId;
    private MyItemClickListener mItemClickListener;
    private MyItemLongClickListener mItemLongClickListener;

    public MyRecyclerAdapter(List<ViewModel> list, int layoutId){
        this.items = list;
        this.itemLayoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayoutId, viewGroup, false);
        return new ViewHolder(v, mItemClickListener, mItemLongClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ViewModel item = items.get(position);
        viewHolder.textView.setText(item.name);
        viewHolder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(MyItemClickListener listener){
        mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(MyItemLongClickListener listener){
        mItemLongClickListener = listener;
    }

    public void add(ViewModel model, int position){
        items.add(position, model);
        notifyItemInserted(position);
    }

    public void remove(int position){
        items.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public TextView textView;
        private MyItemClickListener mOnItemClickListener;
        private MyItemLongClickListener mOnLongClickListener;

        public ViewHolder(View itemView , MyItemClickListener clickListener, MyItemLongClickListener longClickListener) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
            this.mOnItemClickListener = clickListener;
            this.mOnLongClickListener = longClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(mOnLongClickListener != null) {
                mOnLongClickListener.onItemLongClick(v, getPosition());
            }
            return true;
        }
    }

}
