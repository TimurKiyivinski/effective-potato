package com.swinburne.timur.assignment5task1.address;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddressDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Addresses.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AddressContract.AddressEntry.TABLE_NAME + "("
            + AddressContract.AddressEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AddressContract.AddressEntry.COLUMN_NAME_NAME + " TEXT,"
            + AddressContract.AddressEntry.COLUMN_NAME_NUMBER + " TEXT,"
            + AddressContract.AddressEntry.COLUMN_NAME_EMAIL + " TEXT)";

    public AddressDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ignore
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ignore
    }
}
