package com.swinburne.timur.assignment5task1;

import android.app.Application;
import android.util.Log;

import com.swinburne.timur.assignment5task1.address.AddressContent;

public class AddressApplication extends Application {
    public AddressApplication() {
    }

    @Override
    public void onCreate() {
        Log.d("Init", "Application started");
        AddressContent.setContext(this);
    }
}
