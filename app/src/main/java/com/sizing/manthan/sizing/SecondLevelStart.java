package com.sizing.manthan.sizing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Manthan on 9/22/2014.
 * Displays the list of levels that needs to be cleared
 */
public class SecondLevelStart extends Activity {
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondlevelstart);

        Button critical = (Button) findViewById(R.id.criticalMonthButton);
        Button battery = (Button) findViewById(R.id.sizeBatteryButton);
        Button pvarray = (Button) findViewById(R.id.sizePVArrayButton);
        Button controller = (Button) findViewById(R.id.sizeControllerButton);
        final Button readyButton = (Button) findViewById(R.id.readyButton3);
        result = (TextView) findViewById(R.id.ResultTextView);
        readyButton.setVisibility(View.INVISIBLE);

        battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("This step cannot be completed until all previous steps are completed");
                readyButton.setVisibility(View.INVISIBLE);
            }
        });
        pvarray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("This step cannot be completed until all previous steps are completed");
                readyButton.setVisibility(View.INVISIBLE);
            }
        });

        controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("This step cannot be completed until all previous steps are completed");
                readyButton.setVisibility(View.INVISIBLE);
            }
        });
        critical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Find the critical Month");
                readyButton.setVisibility(View.VISIBLE);
            }
        });
        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openSecondLevel = new Intent(SecondLevelStart.this, SecondLevelSecond.class);
                startActivity(openSecondLevel);
            }
        });

    }
}
