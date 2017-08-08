package com.sc.samples.codesnippet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sc.samples.R;
import com.sc.samples.codesnippet.sql.Book;
import com.sc.samples.codesnippet.sql.MySQLiteHelper;

import java.util.List;

/**
 *
 */
public class SQLiteSDCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_sdcard);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "创建数据库", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                MySQLiteHelper db = new MySQLiteHelper(SQLiteSDCardActivity.this);
                /**
                 * CRUD Operations
                 * */
                // add Books
                db.addBook(new Book("Android Application Development Cookbook", "Wei Meng Lee"));
                db.addBook(new Book("Android Programming: The Big Nerd Ranch Guide", "Bill Phillips and Brian Hardy"));
                db.addBook(new Book("Learn Android App Development", "Wallace Jackson"));

                // get all books
                List<Book> list = db.getAllBooks();

                // delete one book
                db.deleteBook(list.get(0));

                // get all books
                db.getAllBooks();

                db.close();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
