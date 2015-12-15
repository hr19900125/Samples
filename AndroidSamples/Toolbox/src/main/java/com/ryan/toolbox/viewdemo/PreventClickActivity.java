package com.ryan.toolbox.viewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ryan.toolbox.R;

/**
 *
 */
public class PreventClickActivity extends Activity{

    private static final int MIN_CLICK_INTERVAL = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevent_click);

        TextView tvCode = (TextView) findViewById(R.id.prevent_double_click_tv_code);
        tvCode.setText(
                " \n \t Code to prevent view from getting clicked more than once \n\n" +
                        " /**\n" +
                        "     * Preventing any view from getting clicked for particular time\n" +
                        "     *\n" +
                        "     * @param view\n" +
                        "     */\n" +
                        "    public static void preventDoubleClick(final View view) {\n" +
                        "        view.setEnabled(false);\n" +
                        "\n" +
                        "        new Handler().postDelayed(new Runnable() {\n" +
                        "\n" +
                        "            @Override\n" +
                        "            public void run() {\n" +
                        "                view.setEnabled(true);\n" +
                        "            }\n" +
                        "        }, MIN_CLICK_INTERVAL);\n" +
                        "    }");

        Button shareBtn = (Button) findViewById(R.id.btnShare);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previewDoubleClick(v);
            }
        });
    }

    private void previewDoubleClick(final View view) {
        view.setEnabled(false);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, MIN_CLICK_INTERVAL);
    }

}
