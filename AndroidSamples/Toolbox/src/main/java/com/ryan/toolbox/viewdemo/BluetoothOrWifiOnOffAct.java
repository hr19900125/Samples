package com.ryan.toolbox.viewdemo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ryan.toolbox.R;
import com.ryan.toolbox.tool.Common;

/**
 *
 */
public class BluetoothOrWifiOnOffAct extends Activity{

    Context mContext;
    Button btnBluetoothOnOff, btnWifiOnOff;
    Boolean isBluetooth = true, isWifi = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_on_off);
        mContext = BluetoothOrWifiOnOffAct.this;
        init();

    }

    private void init() {
        // TODO Auto-generated method stub

        btnBluetoothOnOff = (Button) findViewById(R.id.btnBluetoothOnOff);
        btnWifiOnOff = (Button) findViewById(R.id.btnWifiOnOff);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            btnBluetoothOnOff.setText("Bluetooth on");
            isBluetooth = false;
        } else {
            isBluetooth = true;
            btnBluetoothOnOff.setText("Bluetooth off");
        }

        WifiManager wm = ((WifiManager) mContext.getSystemService(Context.WIFI_SERVICE));
        if (wm.isWifiEnabled()) {
            btnWifiOnOff.setText("Wifi on");
            isWifi = false;
        } else {
            btnWifiOnOff.setText("Wifi off");
            isWifi = true;
        }

        btnBluetoothOnOff.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isBluetooth) {
                    Common.onBlueTooth("on");
                    btnBluetoothOnOff.setText("Bluetooth on");
                    isBluetooth = false;
                } else {
                    Common.onBlueTooth("off");
                    isBluetooth = true;
                    btnBluetoothOnOff.setText("Bluetooth off");
                }
            }
        });

        btnWifiOnOff.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (isWifi) {
                    Common.onWifi(mContext, "on");
                    btnWifiOnOff.setText("Wifi on");
                    isWifi = false;
                } else {
                    Common.onWifi(mContext, "off");
                    btnWifiOnOff.setText("Wifi off");
                    isWifi = true;
                }

            }
        });

    }

}
