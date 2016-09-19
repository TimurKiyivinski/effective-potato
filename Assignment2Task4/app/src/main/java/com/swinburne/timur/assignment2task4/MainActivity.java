package com.swinburne.timur.assignment2task4;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton radioBean = (RadioButton) findViewById(R.id.radioBean);
        RadioButton radioKenny = (RadioButton) findViewById(R.id.radioKenny);
        RadioButton radioBar = (RadioButton) findViewById(R.id.radioBartholomew);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getCheckedRadioButtonId() == R.id.radioBean) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.bean));
                } else if (group.getCheckedRadioButtonId() == R.id.radioKenny) {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.kenny));
                } else if (group.getCheckedRadioButtonId() == R.id.radioBartholomew){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.bartholomew));
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            radioGroup.setOrientation(RadioGroup.VERTICAL);
        } else {
            radioGroup.setOrientation(RadioGroup.HORIZONTAL);
        }
    }
}
