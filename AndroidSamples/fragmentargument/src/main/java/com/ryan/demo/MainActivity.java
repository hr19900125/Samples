package com.ryan.demo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new TestFragment("param")).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.e(TAG, "onPrepareOptionsMenu");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.e(TAG, "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public static class TestFragment extends Fragment {
        private static final String LOG_TAG = "TestFragment";

        private String mArg = "non-param";

        public TestFragment() {
            Log.e(LOG_TAG, "TestFragment non-parameter constructor");
        }

        public TestFragment(String arg) {
            mArg = arg;
            Log.e(LOG_TAG, "TestFragment construct with parameter");
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.e(LOG_TAG, "onCreate");
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            Log.e(LOG_TAG, "onAttach");
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.e(LOG_TAG, "onStart");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.e(LOG_TAG, "onResume");
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.e(LOG_TAG, "onPause");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.e(LOG_TAG, "onStop");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            Log.e(LOG_TAG, "onDetach");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.e(LOG_TAG, "onDestroy");
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            Log.e(LOG_TAG, "onSaveInstanceState");
        }

        @Override
        public void onInflate(AttributeSet attrs, Bundle savedInstanceState) {
            super.onInflate(attrs, savedInstanceState);
            Log.e(LOG_TAG, "onInflate");
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.e(LOG_TAG, "onActivityCreated");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Log.e(LOG_TAG, "onCreateView");
            View rootView = inflater.inflate(R.layout.test_frag, container, false);
            TextView tv = (TextView) rootView.findViewById(R.id.text);
            tv.setText(mArg);
            return rootView;
        }
    }
}
