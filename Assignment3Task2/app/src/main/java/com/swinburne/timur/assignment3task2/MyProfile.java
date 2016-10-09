package com.swinburne.timur.assignment3task2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity implements View.OnClickListener {

    /**
     * Set layout
     *
     * @param savedInstanceState Application context
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Button getProfileButton = (Button) findViewById(R.id.getProfileButton);
        getProfileButton.setOnClickListener(this);
    }

    /**
     * Create intent to get profile data
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, GetProfile.class);
        startActivityForResult(intent, 1);
    }

    /**
     * Change view to display profile if parcel received
     *
     * @param requestCode Request code of previous intent
     * @param resultCode Result code of previous intent
     * @param data Data parcel
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            ProfileParcel profile = data.getParcelableExtra("profile");

            String name = profile.getName();
            String profession = profile.getProfession();
            final String contact = profile.getContact();
            String character = profile.getCharacter();

            this.setContentView(R.layout.activity_profile_view);
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            TextView textViewName = (TextView) findViewById(R.id.textViewName);
            TextView textViewProfession = (TextView) findViewById(R.id.textViewProfession);
            TextView textViewContact = (TextView) findViewById(R.id.textViewContact);

            textViewName.setText(name);
            textViewProfession.setText(profession);

            if (character.equals("Batman")) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.batman));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.superman));
            }

            textViewContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + contact));
                    try {
                        startActivity(intent);
                    } catch (SecurityException e) {
                        // Swallow exception
                    }
                }
            });
        }
    }
}
