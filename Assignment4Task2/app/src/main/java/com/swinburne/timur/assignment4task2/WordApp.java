package com.swinburne.timur.assignment4task2;

import android.app.Application;

import com.swinburne.timur.assignment4task2.word.WordContent;

/**
 * This class is used to call the WordContent loader
 * such that the WordContent class can make use of
 * resources within a static context. Using Application
 * means the instance is only called once, which is optimal
 * for the use case of the application
 */
public class WordApp extends Application {
    public WordApp() {
        // Empty
    }

    @Override
    public void onCreate() {
        WordContent.setContext(this);
    }
}
