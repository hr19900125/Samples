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
                "Validations", /* 0 */
                "Internet availability", /* 1 */
                "Date formats", /* 2 */
                "Device id", /* 3 */
                "Set preferences", /* 4 */
                "Get preferences", /* 5 */
                "Get current location", /* 6 */
                "Pinchzoom image", /* 7 */
                "Get application icon", /* 8 */
                "Send notification", /* 9 */
                "Get random character from A to Z",/* 10 */
                "Screen sleep mode on off",/* 11 */
                "Open url in browser",/* 12 */
                "Show address on map",/* 13 */
                "Download image from url", /* 14 */
                "Open date picker",/* 15 */
                "Open time picker",/* 16 */
                "Get device height",/* 17 */
                "Get device width",/* 18 */
                "Postfix for number",/* 19 */
                "Music ON OFF",/* 20 */
                "Apply blur bffect on image",/* 21 */
                "Set and get image from preferences",/* 22 */
                "Application version",/* 23 */
                "Vertical text views",/* 24 */
                "Is SDCard available?",/* 25 */
                "Show share dialog",/* 26 */
                "Change device profile",/* 27 */
                "Change bitmap to rounded cornered",/* 28 */
                "Prevent double click",/* 29 */
                "Bluetooth/wifi ON OFF",/* 30 */
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
                intent = new Intent(mContext, GetCurrentLocationAct.class);
                break;
            case 7:
                intent = new Intent(mContext, PinchZoomImageViewAct.class);
                break;
            case 8:
                intent = new Intent(mContext, AppIconAct.class);
                break;
            case 9:
                intent = new Intent(mContext, SendLocatoNotificationAct.class);
                break;
            case 10:
//                Toast.makeText(mContext, Common.getRandomCharacter(), Toast.LENGTH_SHORT).show();
                Common.showAlertDialog(mContext, "", Common.getRandomCharacter()+"", false);
                break;
            case 11:
                intent = new Intent(mContext, ScreenModeDis_Ean_Act.class);
                break;
            case 12:
                intent = new Intent(mContext, OpenUrlINBrowserAct.class);
                break;
            case 13:
                intent = new Intent(mContext, AddressOnMapActivity.class);
                break;
            case 14:
                intent = new Intent(mContext, DownloadImageFromUrlAct.class);
                break;
            case 15:
                Common.showDatePickerDialog(mContext, "yyyy-MM-dd");
                break;
            case 16:
                Common.showTimePickerDialog(mContext);
                break;
            case 17:
                Common.showAlertDialog(this, getString(R.string.app_name), "Your device height is: " + Common.getDeviceHeight(mContext) + ".", false);
                break;
            case 18:
                Common.showAlertDialog(this, getString(R.string.app_name), "Your device width is: " + Common.getDeviceWidth(mContext) + ".", false);
                break;
            case 19:
                intent = new Intent(mContext, PostFixForNumberAct.class);
                break;
            case 20:
                intent = new Intent(mContext, BackGroundMusicStartStopAct.class);
                break;
            case 21:
                intent = new Intent(mContext, BlurEffectActivity.class);
                break;
            case 22:
                intent = new Intent(mContext, SaveImageInPreferenceAct.class);
                break;
            case 23:
                Common.showAlertDialog(this, getString(R.string.app_name), "Your application version is: " + Common.getAppVersionCode(mContext) + ".", false);
                break;
            case 24:
                intent = new Intent(mContext, VerticalTextViewsAct.class);
                break;
            case 25:
                if (Common.isSDCardAvailable(mContext))
                    Common.showAlertDialog(this, getString(R.string.app_name), "SDCard is available.", false);
                else
                    Common.showAlertDialog(this, getString(R.string.app_name), "SDCard is not available.", false);
                break;
            case 26:
                Common.openShareDialog(mContext, "Share title", "", "Share text", "Share subject");
                break;
            case 27:
                intent = new Intent(mContext, ChooseProfileActivity.class);
                break;
            case 28:
                intent = new Intent(mContext, RoundedBitmapActivity.class);
                break;
            case 29:
                intent = new Intent(mContext, PreventClickActivity.class);
                break;
            case 30:
                intent = new Intent(mContext, BluetoothOrWifiOnOffAct.class);
                break;

        }
        if(intent != null)startActivity(intent);
    }
}
