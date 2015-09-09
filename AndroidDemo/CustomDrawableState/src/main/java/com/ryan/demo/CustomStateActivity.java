package com.ryan.demo;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CustomStateActivity extends ListActivity {

    private Message[] messages = new Message[] {
            new Message("Gas bill overdue", true),
            new Message("Congratulations, you've won!", true),
            new Message("I love you!", false),
            new Message("Please reply!", false),
            new Message("You ignoring me?", false),
            new Message("Not heard from you", false),
            new Message("Electricity bill", true),
            new Message("Gas bill", true), new Message("Holiday plans", false),
            new Message("Marketing stuff", false), };

    private static class Message {

        private String subject;
        private boolean readed;

        private Message(String subject, boolean readed) {
            this.subject = subject;
            this.readed = readed;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        getListView().setAdapter(new ArrayAdapter<Message>(this, -1, messages){

            private LayoutInflater mInflater = LayoutInflater.from(getContext());

            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                if (convertView == null){
                    convertView = mInflater.inflate(R.layout.message_item,parent, false);
                }
                MessageListItem messageListItem = (MessageListItem) convertView;
                TextView tv = (TextView) convertView
                        .findViewById(R.id.id_msg_item_text);
                tv.setText(getItem(position).subject);
                messageListItem.setMessageReaded(getItem(position).readed);
                return convertView;
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
