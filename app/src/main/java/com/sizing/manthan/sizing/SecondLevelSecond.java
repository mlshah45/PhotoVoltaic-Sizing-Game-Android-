package com.sizing.manthan.sizing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Manthan on 9/22/2014.
 */
public class SecondLevelSecond extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondlevelsecond);

        TextView result = (TextView) findViewById(R.id.second2ndTextView);
        Button continueButton = (Button) findViewById(R.id.Continue2nd2Button);

        result.setText("In the following game you will be determining peak sun hours (PSH) for the most appropriate month (critical design month) for a small DC lighting system with constant loads as well as the most appropriate array orientation and tilt angle for the conditions of that month for a location in the Cook islands");
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openSecondLevel = new Intent(SecondLevelSecond.this, SecondLevel.class);
                startActivity(openSecondLevel);
            }
        });

    }
}
