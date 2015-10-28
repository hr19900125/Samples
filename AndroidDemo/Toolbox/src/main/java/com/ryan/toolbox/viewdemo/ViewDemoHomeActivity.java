package com.ryan.toolbox.viewdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ryan.toolbox.R;
import com.ryan.toolbox.tool.Common;

/**
 *
 */
public class ViewDemoHomeActivity extends Activity{

    private Context mContext;
    private ListView sdkFunctionalityList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_demo_act);
        mContext = this;

        String[] sdkFunctionalityListValue = new String[]{
                "Validations",
                "Internet availability",
                "Date formats",
                "Device id",
                "Set preferences",
                "Get preferences",
                "Get current location"
        };
        sdkFunctionalityList = (ListView) findViewById(R.id.demo_list);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sdkFunctionalityListValue);
        sdkFunctionalityList.setAdapter(stringArrayAdapter);

        sdkFunctionalityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                selectedListItem(position);
            }

        });

    }

    private void selectedListItem(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(mContext, ValidationsAct.class);
                break;
            case 1:
                if (Common.isNetworkAvailable(mContext))
                    Common.showAlertDialog(this, getString(R.string.app_name), "Internet connection is available.", false);
                else
                    Common.showAlertDialog(this, getString(R.string.app_name), "Internet connection is not available.", false);
                break;
            case 2:
                intent = new Intent(mContext, DateFormateAct.class);
                break;
            case 3:
                Common.showAlertDialog(this, "", "Your device id is: " + Common.getDeviceId(mContext) + ".", false);
                break;
            case 4:
                intent = new Intent(mContext, SetPrefAct.class);
                break;
            case 5:
                intent = new Intent(mContext, GetPrefAct.class);
                break;
            case 6:

                break;
        }
        if(intent != null)startActivity(intent);
    }
}
