package com.sc.samples.example;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.sc.samples.R;

/**
 *
 */
public class ActLifeCycle2Activity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_act_life_cycle);
    }
}
