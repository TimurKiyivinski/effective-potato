package com.swinburne.timur.assignment2task3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView cupInput = (TextView) findViewById(R.id.editTextCup);
        final TextView tableSpoonInput = (TextView) findViewById(R.id.editTextTableSpoon);
        final TextView teaSpoonInput = (TextView) findViewById(R.id.editTextTeaSpoon);
        final CheckBox litreInput = (CheckBox) findViewById(R.id.checkBox);
        final TextView outputText = (TextView) findViewById(R.id.resultText);
        Button enterButton = (Button) findViewById(R.id.button);

        enterButton.setOnClickListener(new View.OnClickListener() {
            private final double CUP_CONVERT = 240;
            private final double TABLE_SPOON_CONVERT = 15;
            private final double TEA_SPOON_CONVERT = 5;

            @Override
            public void onClick(View v) {
                try {
                    Double cup = Double.valueOf(cupInput.getText().toString());
                    Double tableSpoon = Double.valueOf(tableSpoonInput.getText().toString());
                    Double teaSpoon = Double.valueOf(teaSpoonInput.getText().toString());

                    Double total = (cup * CUP_CONVERT) +
                            (tableSpoon * TABLE_SPOON_CONVERT) +
                            (teaSpoon * TEA_SPOON_CONVERT);

                    NumberFormat totalFormat = new DecimalFormat("#0.000");

                    String outputString = litreInput.isChecked() ?
                            getResources().getString(R.string.str_output) + " " + totalFormat.format(total / 1000).toString() + "l" :
                            getResources().getString(R.string.str_output) + " " + total.toString() + "ml";

                    outputText.setText(outputString);
                } catch (NumberFormatException e) {
                    outputText.setText(getResources().getString(R.string.str_output_error));
                }
            }
        });
    }
}
