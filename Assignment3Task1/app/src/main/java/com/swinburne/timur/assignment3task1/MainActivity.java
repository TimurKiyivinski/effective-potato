package com.swinburne.timur.assignment3task1;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create constant dialog so can be used in overriden interfaces
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.harambe_dialog);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * Click handler for button
             *
             * @param v Parent view
             */
            @Override
            public void onClick(View v) {
                dialog.show();
                Button dialogButton = (Button) dialog.findViewById(R.id.button_dismiss);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    /**
                     * Click handler for button
                     *
                     * @param v Parent view
                     */
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                    }
                });
            }
        });

    }
}
