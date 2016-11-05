package com.swinburne.timur.assignment5task1.address;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

/**
 * Address CRUD interface
 */
public class AddressContent {

    /**
     * An array of sample (address) items.
     */
    public static final List<Address> ITEMS = new ArrayList<Address>();
    public static Context context;

    /**
     * A map of sample (address) items, by ID.
     */
    public static Map<String, Address> ITEM_MAP = new HashMap<String, Address>();

    /**
     * Set global application context and load database
     * @param context
     */
    public static void setContext(Context context) {
        AddressContent.context = context;
        // Load values
        String[] projection = {
                AddressContract.AddressEntry.COLUMN_NAME_ID,
                AddressContract.AddressEntry.COLUMN_NAME_NAME,
                AddressContract.AddressEntry.COLUMN_NAME_NUMBER,
                AddressContract.AddressEntry.COLUMN_NAME_EMAIL
        };
        AddressDbHelper dbHelper = new AddressDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Execute query
        Cursor cursor = db.rawQuery("SELECT * FROM " + AddressContract.AddressEntry.TABLE_NAME, null);
        cursor.moveToFirst();

        // Get all results
        if (cursor.moveToFirst()) {
            do {
                int id = (int) cursor.getLong(0);
                String name = cursor.getString(1);
                String number = cursor.getString(2);
                String email = cursor.getString(3);
                Address address = new Address(id, name, number, email);
                Log.d("Address", address.toString());
                addItem(address);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
    }

    /**
     * Adds Address to SQLite database and local context
     * @param item Address item
     */
    public static void addItem(Address item) {
        // Only insert into database if ID is not initialized
        if (item.getID() == 0) {
            AddressDbHelper dbHelper = new AddressDbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(AddressContract.AddressEntry.COLUMN_NAME_NAME, item.getName());
            values.put(AddressContract.AddressEntry.COLUMN_NAME_NUMBER, item.getNumber());
            values.put(AddressContract.AddressEntry.COLUMN_NAME_EMAIL, item.getEmail());

            // Insert row
            long newID = db.insert(AddressContract.AddressEntry.TABLE_NAME, null, values);
            db.close();
            item.setID((int) newID); // Nobody will reach a 64 bit count of contacts o.o
            Log.d("CREATE", "New Address with ID: " + valueOf(newID));
        }

        // Update local store
        ITEMS.add(item);
        ITEM_MAP.put(item.getID().toString(), item);
    }

    /**
     * Updates Address SQLite item and local context based on ID
     * @param id Address item ID
     * @param item Address item
     */
    public static void updateItem(String id, Address item) {
        Log.d("UPDATE", id + ": " + item.toString());
        AddressDbHelper dbHelper = new AddressDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(AddressContract.AddressEntry.COLUMN_NAME_NAME, item.getName());
        values.put(AddressContract.AddressEntry.COLUMN_NAME_NUMBER, item.getNumber());
        values.put(AddressContract.AddressEntry.COLUMN_NAME_EMAIL, item.getEmail());

        String selection = AddressContract.AddressEntry.COLUMN_NAME_ID + " =? ";
        String[] selectionArgs = { id };

        int count = db.update(AddressContract.AddressEntry.TABLE_NAME, values, selection, selectionArgs);
        db.close();
        Log.d("UPDATE", valueOf(count) + " rows updated");

        item.setID(Integer.valueOf(id));
        ITEM_MAP.put(id, item);
        for (int i = 0; i < ITEMS.size(); i++) {
            Address mValue = ITEMS.get(i);
            if (mValue.getID().toString().equals(id)) {
                ITEMS.set(i, item);
            }
        }
    }

    /**
     * Remove Address item from SQLite and local context based on ID
     * @param id Address item ID
     */
    public static void removeItem(String id) {
        AddressDbHelper dbHelper = new AddressDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = AddressContract.AddressEntry.COLUMN_NAME_ID + " =? ";
        String[] selectionArgs = { id };

        db.delete(AddressContract.AddressEntry.TABLE_NAME, selection, selectionArgs);
        ITEM_MAP.remove(id);
        for (int i = 0; i < ITEMS.size(); i++) {
            Address mValue = ITEMS.get(i);
            if (mValue.getID().toString().equals(id)) {
                Log.d("REMOVE", mValue.toString());
                ITEMS.remove(i);
            }
        }
    }
}
