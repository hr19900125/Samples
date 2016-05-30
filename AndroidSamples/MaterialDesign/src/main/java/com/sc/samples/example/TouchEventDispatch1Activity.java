package com.sc.samples.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sc.samples.R;

/**
 *
 */
public class TouchEventDispatch1Activity extends Activity implements View.OnTouchListener, View.OnClickListener{

    private LinearLayout mLayout;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchevent_dispatch_1);

        mLayout = (LinearLayout) findViewById(R.id.layout);
        mButton = (Button) findViewById(R.id.btn);

        mLayout.setOnTouchListener(this);
        mButton.setOnTouchListener(this);

        mLayout.setOnClickListener(this);
        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.i(null, "OnClickListener--onClick--"+v);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i(null, "OnTouchListener--onTouch-- action="+event.getAction()+" --"+v);
        return false;
    }
}
