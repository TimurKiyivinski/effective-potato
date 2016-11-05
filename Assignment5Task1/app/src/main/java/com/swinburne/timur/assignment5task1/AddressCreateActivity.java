package com.swinburne.timur.assignment5task1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.swinburne.timur.assignment5task1.address.Address;
import com.swinburne.timur.assignment5task1.address.AddressContent;

/**
 * An activity representing a single Address detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link AddressListActivity}.
 */
public class AddressCreateActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.create_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up call_button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);
    }

    /**
     * Handle save button event
     * @param v
     */
    @Override
    public void onClick(View v) {
        EditText editName = (EditText) findViewById(R.id.address_create_name);
        EditText editPhone = (EditText) findViewById(R.id.address_create_phone);
        EditText editEmail = (EditText) findViewById(R.id.address_create_email);

        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();
        String email = editEmail.getText().toString();

        if (Patterns.PHONE.matcher(phone).matches()) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Address newAddress = new Address(name, phone, email);
                AddressContent.addItem(newAddress);

                // Create confirmation toast
                Toast toast = Toast.makeText(this, getString(R.string.toast_contact_added), 5);
                toast.show();

                // Return to previous view
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            } else {
                Toast toast = Toast.makeText(this, getString(R.string.toast_error_email), 5);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(this, getString(R.string.toast_error_number), 5);
            toast.show();
        }
    }
}
