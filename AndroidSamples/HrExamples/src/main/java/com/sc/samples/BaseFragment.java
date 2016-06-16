package com.sc.samples;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 *
 */
public class BaseFragment extends Fragment implements OnBackPressedListener {

    protected OnSelectedFragmentDelegate mOnSelectedFragmentDelegate;

    public interface OnSelectedFragmentDelegate {
        public void setupSelectedFragment(BaseFragment fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof OnSelectedFragmentDelegate)) {
            throw new ClassCastException("Hosting activity must implement OnSelectedFragmentDelegate");
        } else {
            mOnSelectedFragmentDelegate = (OnSelectedFragmentDelegate) getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        mOnSelectedFragmentDelegate.setupSelectedFragment(this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
