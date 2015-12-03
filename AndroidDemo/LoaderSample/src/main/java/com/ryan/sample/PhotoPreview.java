package com.ryan.sample;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PhotoPreview extends Activity implements ImageItemClickListener{

    private final int LOADER_ID = 10001;
    private String mCurrentDirectory;
    private RecyclerView mRecyclerView;
    private View mNoResultView;
    private LoaderManager.LoaderCallbacks<ImageInfoResult> mCallbacks;
    private List<ImageInfo> mImageInfoList;
    private ImageRecyclerViewAdapter mAdapter;
    private List<ImageInfo> mImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_preview);
        Config.initImageLoader(this);
        init();
    }

    private void init() {
        mCurrentDirectory = getIntent().getStringExtra(Config.CURRENT_DIRECTORY);
        if(TextUtils.isEmpty(mCurrentDirectory)) {
            mCurrentDirectory = Environment.getExternalStorageDirectory().getPath() + "/" + Environment.DIRECTORY_DCIM;
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.photo_grid_view);
        mNoResultView = findViewById(R.id.no_result);
        mImageList = new ArrayList<ImageInfo>();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter = new ImageRecyclerViewAdapter(mImageList);
        mAdapter.setItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        initLoader();
    }

    private void initLoader() {
        mCallbacks = new LoaderManager.LoaderCallbacks<ImageInfoResult>() {

            @Override
            public Loader<ImageInfoResult> onCreateLoader(int id, Bundle args) {
                return new ImageInfoLoader(PhotoPreview.this, mCurrentDirectory);
            }

            @Override
            public void onLoadFinished(Loader<ImageInfoResult> loader, ImageInfoResult result) {
                try {
                    if(result == null) {
                        return;
                    }

                    if(result.imageInfos == null) {
                        result.imageInfos = Collections.EMPTY_LIST;
                    }
                    mImageInfoList = result.imageInfos;
                    mAdapter = new ImageRecyclerViewAdapter(result.imageInfos);
                    mAdapter.setItemClickListener(PhotoPreview.this);
                    mRecyclerView.setAdapter(mAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoaderReset(Loader<ImageInfoResult> loader) {

            }
        };
        getLoaderManager().restartLoader(LOADER_ID, null, mCallbacks);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        String filePath = mImageInfoList.get(position).filePath;
        if(TextUtils.isEmpty(filePath))return;
        openImage(filePath);
    }

    private void openImage(String path) {
        String mimeType = MimeTypeUtils.getMimeType(path);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.fromFile( new File(path));
        intent.setDataAndType(data, mimeType);
        try {
//            intent.putExtra(Intent.EXTRA_TITLE,getString(R.string.openwith));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
