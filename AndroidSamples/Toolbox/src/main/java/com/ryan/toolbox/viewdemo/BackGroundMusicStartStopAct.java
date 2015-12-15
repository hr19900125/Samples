package com.ryan.toolbox.viewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ryan.toolbox.R;
import com.ryan.toolbox.tool.Common;

/**
 *
 */
public class BackGroundMusicStartStopAct extends Activity{

    Context mContext;
    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_music_start_stop);
        mContext = BackGroundMusicStartStopAct.this;
        init();

    }

    private void init() {
        // TODO Auto-generated method stub
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Common.backgroundMusicStart(mContext);
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Common.backgroundMusicStop(mContext);
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Common.backgroundMusicStop(mContext);
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
    }

}
