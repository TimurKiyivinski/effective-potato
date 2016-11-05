package com.swinburne.timur.assignment5task1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.swinburne.timur.assignment5task1.address.Address;
import com.swinburne.timur.assignment5task1.address.AddressContent;

/**
 * An activity representing a single Address detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link AddressListActivity}.
 */
public class AddressDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Show the Up call_button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(AddressDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(AddressDetailFragment.ARG_ITEM_ID));
            AddressDetailFragment fragment = new AddressDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.address_detail_container, fragment)
                    .commit();
        }
    }

    /**
     * Initialize menu
     * @param menu Menu pointer
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Set menu layout
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handle toolbar events
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up call_button. In the case of this
            // activity, the Up call_button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, AddressListActivity.class));
            return true;
        } else if (id == R.id.menu_edit) {
            // Edit button selected
            EditText editName = (EditText) findViewById(R.id.address_name);
            EditText editPhone = (EditText) findViewById(R.id.address_phone);
            EditText editEmail = (EditText) findViewById(R.id.address_email);

            // Remove call_button
            Button callButton = (Button) findViewById(R.id.call_button);
            ((ViewGroup) callButton.getParent()).removeView(callButton);

            editName.setEnabled(true);
            editPhone.setEnabled(true);
            editEmail.setEnabled(true);

            // Add save button
            Button saveButton = new Button(this);
            saveButton.setText(getString(R.string.address_save));
            ViewGroup.LayoutParams saveLayout = callButton.getLayoutParams(); // Reuse layout params from callButton
            ((ViewGroup) findViewById(R.id.detailLayout)).addView(saveButton, saveLayout);
            saveButton.setOnClickListener(this);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Handle save button event
     * @param v
     */
    @Override
    public void onClick(View v) {
        EditText editName = (EditText) findViewById(R.id.address_name);
        EditText editPhone = (EditText) findViewById(R.id.address_phone);
        EditText editEmail = (EditText) findViewById(R.id.address_email);

        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();
        String email = editEmail.getText().toString();

        if (Patterns.PHONE.matcher(phone).matches()) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Address newAddress = new Address(name, phone, email);
                AddressContent.updateItem(getIntent().getStringExtra(AddressDetailFragment.ARG_ITEM_ID), newAddress);

                // Create confirmation toast
                Toast toast = Toast.makeText(this, getString(R.string.toast_contact_updated), 5);
                toast.show();

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
