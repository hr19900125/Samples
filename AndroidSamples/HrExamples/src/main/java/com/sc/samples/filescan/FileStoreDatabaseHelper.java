package com.sc.samples.filescan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */
public class FileStoreDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "file_store.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FILES = "file_info";

    public FileStoreDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_FILES + " ("
                + FileInfoColumns.FILE_ID + " INTEGER PRIMARY KEY,"
                + FileInfoColumns.FILE_PATH + " TEXT NOT NULL,"
                + FileInfoColumns.FILE_NAME + " TEXT NOT NULL,"
                + FileInfoColumns.FILE_TYPE + " INTEGER DEFAULT 0,"
                + FileInfoColumns.SIZE + " INTEGER DEFAULT 0,"
                + FileInfoColumns.MODIFIED_DATE + " INTEGER DEFAULT 0,"
                + FileInfoColumns.MIME_TYPE + " TEXT,"
                + FileInfoColumns.TITLE + " TEXT,"
                + FileInfoColumns.THUMBNAIL_PATH + " TEXT,"
                + FileInfoColumns.IMG_WIDTH + " INTEGER DEFAULT 0,"
                + FileInfoColumns.IMG_HEIGHT + " INTEGER DEFAULT 0,"
                + FileInfoColumns.FLAG + " INTEGER DEFAULT 0,"
                + FileInfoColumns.EXT2 + " TEXT,"
                + FileInfoColumns.EXT1 + " TEXT,"
                + FileInfoColumns.IS_NEW + " INTEGER DEFAULT 0,"
                + FileInfoColumns.APK_LABEL + " TEXT"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILES);
        onCreate(db);
    }
}
