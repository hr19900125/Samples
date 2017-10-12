package com.sc.samples.codesnippet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.sc.samples.R;

/**
 *
 */

public class NetworkStateChangeActivity extends AppCompatActivity {

    private static final String TAG = "NetworkChanged";

    private TextView mResultTv;
    private StringBuffer mSb;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_state_change);
        mResultTv = (TextView) findViewById(R.id.result);
        mSb = new StringBuffer();
        registerReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    private void registerReceiver() {
        if (mReceiver == null) {
            mReceiver = new NetworkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, filter);
    }

    private void unregisterReceiver() {
        unregisterReceiver(mReceiver);
    }

    private class NetworkStateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //监听wifi的打开与关闭，与wifi的连接无关
            if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
                Log.e(TAG, "wifiState : " + wifiState);
                mSb.append("wifiState : " + wifiState + "\n");
                switch (wifiState) {
                    case WifiManager.WIFI_STATE_DISABLED:
                        Log.e(TAG, "wifiState : disabled");
                        mSb.append("wifiState : disabled\n");
                        break;
                    case WifiManager.WIFI_STATE_DISABLING:
                        Log.e(TAG, "wifiState : disabling");
                        mSb.append("wifiState : disabling\n");
                        break;
                    case WifiManager.WIFI_STATE_ENABLED:
                        Log.e(TAG, "wifiState : enabled");
                        mSb.append("wifiState : enabled\n");
                        break;
                    case WifiManager.WIFI_STATE_ENABLING:
                        Log.e(TAG, "wifiState : enabling");
                        mSb.append("wifiState : enabling\n");
                        break;
                    case WifiManager.WIFI_STATE_UNKNOWN:
                        Log.e(TAG, "wifiState : unknown");
                        mSb.append("wifiState : unknown\n");
                        break;
                }
            }

            //监听是否连接上了一个有效无限路由，当上边广播的状态是WifiManager.WIFI_STATE_DISABLING,和WIFI_STATE_DISABLED的时候，不会接收到该广播
            //当上边接收到WIFI_STATE_ENABLED状态的同时也会接收到这个广播
            if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (parcelableExtra != null) {
                    NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                    NetworkInfo.State state = networkInfo.getState();
                    boolean isConnected = state == NetworkInfo.State.CONNECTED;
                    Log.e(TAG, "isConnected : " + isConnected);
                    mSb.append("isConnected : " + isConnected + "\n");
                }
            }

            //这个监听网络连接的设置，包括wifi和移动数据的打开和关闭
            //wifi如果打开，关闭，以及连接上可用的连接都会接到监听。
            //这个广播的最大弊端就是比上边两个广播的反应要慢
            //如果只是要监听wifi，适合使用上面两个广播
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                Log.e(TAG, "connectivity action");
                mSb.append("connectivity action\n");

                NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    if (activeNetwork.isConnected()) {
                        if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                            Log.e(TAG, "当前WIFI连接可用");
                            mSb.append("当前WIFI连接可用\n");
                        } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                            Log.e(TAG, "当前移动网络可用");
                            mSb.append("当前移动网络可用\n");
                        }
                    } else {
                        Log.e(TAG, "当前没有网络连接，请确保你已经打开网络");
                        mSb.append("当前没有网络连接，请确保你已经打开网络\n");
                    }
                } else {
                    Log.e(TAG, "当前没有网络连接，请确保你已经打开网络");
                    mSb.append("当前没有网络连接，请确保你已经打开网络\n");
                }
            }

            mResultTv.setText(mSb.toString());
        }
    }
}
