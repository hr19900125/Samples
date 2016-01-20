package com.ryan.toolbox.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ryan.toolbox.R;

/**
 *
 */
public class CameraSamplesActivity extends Activity{

    private Context mContext;
    private ListView mSamplesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_demo_act);

        mContext = this;

        String[] samplesValues = new String[] {
                "TextureView Simple sample"
        };

        mSamplesListView = (ListView) findViewById(R.id.demo_list);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, samplesValues);
        mSamplesListView.setAdapter(stringArrayAdapter);
        mSamplesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                selectedListItem(position);
            }
        });
    }

    private void selectedListItem(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(mContext, LiveCameraActivity.class);
                startActivity(intent);
                break;
        }
    }



}
