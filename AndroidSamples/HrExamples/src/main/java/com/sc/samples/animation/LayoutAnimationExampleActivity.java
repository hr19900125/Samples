package com.sc.samples.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.sc.samples.R;

import java.util.ArrayList;
import java.util.List;

/**
 * XML 实现LayoutAnimation
 * http://blog.csdn.net/harvic880925/article/details/50785786
 */
public class LayoutAnimationExampleActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private Button mAddButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation_example);
        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData());
        mListView.setAdapter(mAdapter);

        mAddButton = (Button) findViewById(R.id.add);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addAll(getData());
            }
        });
    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");
        return data;
    }
}
