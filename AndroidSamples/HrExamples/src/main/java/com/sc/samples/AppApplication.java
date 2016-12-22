package com.sc.samples;

import android.app.Application;

import com.sc.samples.gen.DaoMaster;
import com.sc.samples.gen.DaoSession;
import com.sc.samples.realm.RealmConstant;

import org.greenrobot.greendao.database.Database;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 *
 */
public class AppApplication extends Application {

    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).name(RealmConstant.MEALM_NAME).deleteRealmIfMigrationNeeded().schemaVersion(RealmConstant.VERSION).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }


}
