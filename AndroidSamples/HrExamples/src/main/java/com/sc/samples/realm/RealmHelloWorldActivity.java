package com.sc.samples.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.R;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 *
 */
public class RealmHelloWorldActivity extends AppCompatActivity {

    private Realm realm;
    private Button mButton;
    private TextView mTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_btn_and_textview);
        realm = Realm.getDefaultInstance();
        mButton = (Button) findViewById(R.id.btn);
        mTextview = (TextView) findViewById(R.id.textview);
        writeRealm1();
        writeRealm2();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmQuery<Dog> query = realm.where(Dog.class);
                RealmResults<Dog> dogs = query.findAll();
                for (int i = 0; i < dogs.size(); i++) {
                    Dog dog = dogs.get(i);
                    Log.e("DogInfo", "dog name : " + dog.getName() + ", age : " + dog.getAge() + ", color : " + dog.getColor());
                }
            }
        });
    }

    /**
     * 写数据库 方式1，realm写必须在一个事务中，即需要beginTransaction 与 commitTransaction
     * <p/>
     * 这种方式会阻塞当前线程
     */
    private void writeRealm1() {
        realm.beginTransaction();
        Dog dog = realm.createObject(Dog.class);
        dog.setName("旺财");
        dog.setAge(2);
        dog.setColor("blue");
        realm.commitTransaction();
    }

    private void writeRealm2() {
        Dog dog = new Dog();
        dog.setName("大黄");
        dog.setAge(3);
        dog.setColor("red");
        realm.beginTransaction();
        realm.copyToRealm(dog);
        realm.commitTransaction();
    }

}
