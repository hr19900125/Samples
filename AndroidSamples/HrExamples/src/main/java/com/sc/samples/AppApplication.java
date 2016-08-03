package com.sc.samples;

import android.app.Application;

import com.sc.samples.realm.RealmConstant;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 *
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).name(RealmConstant.MEALM_NAME).deleteRealmIfMigrationNeeded().schemaVersion(RealmConstant.VERSION).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
