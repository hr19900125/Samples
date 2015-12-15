package com.ryan.toolbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ryan.toolbox.codesnippet.CodeSnippetHomeActivity;
import com.ryan.toolbox.material.MaterialLibrariesHomeActivity;
import com.ryan.toolbox.viewdemo.ViewDemoHomeActivity;


public class HomeActivity extends Activity {

    Context mContext;
    Activity mActivity;
    TextView txtDemo, txtCodeSnip, txtMaterialLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mContext = HomeActivity.this;
        mActivity = HomeActivity.this;

        txtDemo = (TextView) findViewById(R.id.txtDemo);
        txtCodeSnip = (TextView) findViewById(R.id.txtCodeSnip);
        txtMaterialLibrary = (TextView) findViewById(R.id.txtMaterialLibrary);

        txtDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intdemo = new Intent(mContext, ViewDemoHomeActivity.class);
                startActivity(intdemo);
            }
        });

        txtCodeSnip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intcode = new Intent(mContext, CodeSnippetHomeActivity.class);
                startActivity(intcode);
            }
        });

        txtMaterialLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intcode = new Intent(mContext, MaterialLibrariesHomeActivity.class);
                startActivity(intcode);
            }
        });
    }
}
