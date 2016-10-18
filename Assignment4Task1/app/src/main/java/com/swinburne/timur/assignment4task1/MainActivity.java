package com.swinburne.timur.assignment4task1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        EditText editTextNationality = (EditText) findViewById(R.id.editTextNationality);
        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        final TextView textViewResults = (TextView) findViewById(R.id.textViewResult);

        String name = URLEncoder.encode(editTextName.getText().toString());
        String nationality = URLEncoder.encode(editTextNationality.getText().toString());
        String email = URLEncoder.encode(editTextEmail.getText().toString());
        String url = "https://cos30017‐server1‐tootsie.c9users.io/upload/?nm="
                + name
                + "&national="
                + nationality
                + "&email="
                + email
                + "&author=Timothy";

        Log.i("URL", url);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textViewResults.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textViewResults.setText(getResources().getString(R.string.error));
                    }
            }
        );

        queue.add(stringRequest);
    }
}
