package com.swinburne.timur.assignment3task2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class GetProfile extends AppCompatActivity implements View.OnClickListener {

    /**
     * Set layout
     *
     * @param savedInstanceState Application context
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_profile);

        Button saveProfileButton = (Button) findViewById(R.id.button);
        saveProfileButton.setOnClickListener(this);
    }

    /**
     * Handle button and parcel to be set back
     * @param v
     */
    @Override
    public void onClick(View v) {
        EditText editName = (EditText) findViewById(R.id.editTextName);
        EditText editProfession = (EditText) findViewById(R.id.editTextProfession);
        EditText editContact = (EditText) findViewById(R.id.editTextContact);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        String name = editName.getText().toString();
        String profession = editProfession.getText().toString();
        String contact = editContact.getText().toString();
        String character = radioGroup.getCheckedRadioButtonId() == R.id.radioButtonBatman ?
                "Batman":
                "Superman";

        // Only send parcel if contact is a number
        if (android.util.Patterns.PHONE.matcher(contact).matches()) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("profile", new ProfileParcel(name, profession, contact, character));

            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } else {
            editContact.setText("");
        }
    }
}
