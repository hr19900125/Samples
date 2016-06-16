package com.sc.samples.example;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sc.samples.R;

/**
 * Fragment 生命周期
 * <p/>
 * 一旦activity进入resumed状态（也就是running状态），你就可以自由地添加和删除fragment了。因此，只有当activity在resumed状态时，
 * fragment的生命周期才能独立的运转，其它时候是依赖于activity的生命周期变化的。
 */
public class FragLifeCycleActivity extends Activity {

    private static final String LOG_TAG = "LifeCycle_Activity";

    private String mStr = "I Can";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "onCreate called");
        setContentView(R.layout.activity_frag_life_cycle);

        getFragmentManager().beginTransaction().replace(R.id.frame_content, new MyFragment()).commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "onRestart called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("param", mStr);
        Log.i(LOG_TAG, "onSaveInstanceState called. put param: " + mStr);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mStr = savedInstanceState.getString("param");
        Log.i(LOG_TAG, "onRestoreInstanceState called. get param: " + mStr);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public static class MyFragment extends Fragment {

        private static final String LOG_TAG = "LifeCycle_Fragment";

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            Log.i(LOG_TAG, "onAttach called");
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.i(LOG_TAG, "onCreate called");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Log.i(LOG_TAG, "onCreateView called");
            return inflater.inflate(R.layout.activity_act_life_cycle, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.i(LOG_TAG, "onActivityCreated called");
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.i(LOG_TAG, "onStart called");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.i(LOG_TAG, "onResume called");
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.i(LOG_TAG, "onPause called");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.i(LOG_TAG, "onStop called");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            Log.i(LOG_TAG, "onDestroyView called");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.i(LOG_TAG, "onDestroy called");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            Log.i(LOG_TAG, "onDetach called");
        }
    }
}
