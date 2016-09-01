package com.sc.samples.design.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sc.samples.R;

import java.util.List;

/**
 *
 */
public class MvpMainActivity extends AppCompatActivity implements MainView, AdapterView.OnItemClickListener {

    private ListView mListView;
    private ProgressBar mProgressBar;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_main);

        mListView = (ListView) findViewById(R.id.list);
        mListView.setOnItemClickListener(this);

        mProgressBar = (ProgressBar) findViewById(R.id.progress);

        mMainPresenter = new MainPresenterImpl(this, new FindItemsInteractorImpl());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mMainPresenter.onItemClicked(position);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
        mListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<String> items) {
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
