package com.ryan.sample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 *
 */
public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder>{

    private List<ImageInfo> mImageList;
    private DisplayImageOptions mOptions;
    private ImageItemClickListener mImageItemClickListener;

    public ImageRecyclerViewAdapter(List<ImageInfo> list) {
        super();
        mImageList = list;
        mOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View contentView = View.inflate(viewGroup.getContext(), R.layout.photo_grid_item, null);
        ViewHolder holder = new ViewHolder(contentView, mImageItemClickListener);
        return holder;
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        ImageView icon = holder.imageView;
        if (icon != null) {
            icon.setImageBitmap(null);
        }
        super.onViewRecycled(holder);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ImageInfo info = mImageList.get(position);
        ImageLoader.getInstance().displayImage("file://" + info.filePath, viewHolder.imageView, mOptions);
    }

    public void setItemClickListener(ImageItemClickListener listener) {
        mImageItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imageView;
        private ImageItemClickListener mItemClickListener;

        public ViewHolder(View itemView, ImageItemClickListener listener) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.photo_thumbnail);
            mItemClickListener = listener;
            imageView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

}
