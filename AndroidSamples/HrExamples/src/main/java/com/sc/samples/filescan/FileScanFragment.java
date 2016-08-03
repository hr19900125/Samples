package com.sc.samples.filescan;

import android.os.Bundle;
import android.os.FileObserver;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.sc.samples.BaseFragment;
import com.sc.samples.R;
import com.sc.samples.filescan.model.FileInfo;

import java.util.HashMap;
import java.util.concurrent.Executors;

import io.realm.Realm;

/**
 * 文件扫描Demo
 */
public class FileScanFragment extends BaseFragment {

    private View mFragmentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(R.layout.fragment_filescan, container, false);
        }
        return mFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        Button startScan = (Button) mFragmentView.findViewById(R.id.start_scan);
        Button stopScan = (Button) mFragmentView.findViewById(R.id.stop_scan);

        startScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanService.startService(getActivity());
            }
        });

        stopScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.clear(FileInfo.class);
                    }
                });
                ScanService.stopService(getActivity());
            }
        });
    }

    @Override

    public void onDestroy() {
        super.onDestroy();
    }

}
