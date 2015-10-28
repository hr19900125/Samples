package com.ryan.toolbox.viewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ryan.toolbox.R;
import com.ryan.toolbox.tool.Common;

/**
 *
 */
public class DateFormateAct extends Activity{

    private TextView date1 , date2, date3, date4, date5;
    private Button btnShowDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_format_act);
        init();
    }

    private void init() {
        date1 = (TextView) findViewById(R.id.tvDateFormate_yyyyMMdd);
        date2 = (TextView) findViewById(R.id.tvDateFormate_yyyyMMddHHmm);
        date3 = (TextView) findViewById(R.id.tvDateFormate_yyyyMMddHHmmZ);
        date4 = (TextView) findViewById(R.id.tvDateFormate_yyyyMMddHHmmssSSSZ);
        date5 = (TextView) findViewById(R.id.tvDateFormate_yyyyMMddTHHmmssSSSZ);

        btnShowDate = (Button) findViewById(R.id.btnShowDate);
        btnShowDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                date1.setText(Common.getCurrentDate("yyyy-MM-dd"));
                date2.setText(Common.getCurrentDate("yyyy-MM-dd HH:mm"));
                date3.setText(Common.getCurrentDate("yyyy-MM-dd HH:mmZ"));
                date4.setText(Common.getCurrentDate("yyyy-MM-dd HH:mm:ss.SSSZ"));
                date5.setText(Common
                        .getCurrentDate("yyyy-MM-dd 'T' HH:mm:ss.SSSZ"));

                date1.setVisibility(View.VISIBLE);
                date2.setVisibility(View.VISIBLE);
                date3.setVisibility(View.VISIBLE);
                date4.setVisibility(View.VISIBLE);
                date5.setVisibility(View.VISIBLE);
            }
        });
    }

}
