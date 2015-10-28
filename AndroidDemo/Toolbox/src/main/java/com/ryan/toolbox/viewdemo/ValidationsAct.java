package com.ryan.toolbox.viewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ryan.toolbox.R;
import com.ryan.toolbox.tool.Common;

/**
 *
 */
public class ValidationsAct extends Activity{

    private Context mContext;
    private EditText etEmpty;
    private Button btnEmpty, btnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validations_act);
        mContext = this;

        init();
    }

    private void init() {
        etEmpty = (EditText) findViewById(R.id.etValidation);
        btnEmpty = (Button) findViewById(R.id.btnEmpty);
        btnEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.isEmptyEditText(etEmpty))
                    Toast.makeText(mContext, "Edittext is empty.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(mContext, "Edittext is not empty.", Toast.LENGTH_SHORT).show();
            }
        });
        btnEmail = (Button) findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.isEmptyEditText(etEmpty)) {
                    Toast.makeText(mContext, "Please enter your email.", Toast.LENGTH_SHORT).show();
                } else {
                    if (Common.isEmailIdValid(etEmpty.getText().toString())) {
                        Toast.makeText(mContext, "Your email is valid.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "Your email is not valid.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
