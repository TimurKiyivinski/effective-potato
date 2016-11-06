package com.swinburne.timur.assignment5task2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((WebView) findViewById(R.id.web_view)).loadUrl("file:///android_asset/index.html");
    }
}
