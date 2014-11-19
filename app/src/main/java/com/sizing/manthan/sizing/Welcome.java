package com.sizing.manthan.sizing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Manthan on 9/5/2014.
 * This is the second activity after the Main
 */
public class Welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        Intent WelcomeI = getIntent();
        String username = WelcomeI.getExtras().getString("EXTRA");

        TextView welcomeTextView = (TextView) findViewById(R.id.TVWelcome);
        TextView infoTextView = (TextView) findViewById(R.id.TVInfo);
        Button continueButton = (Button) findViewById(R.id.BNext);

        welcomeTextView.setText("Welcome " + username + ",");
        infoTextView.setText("Welcome! You are about to take an interactive journey to sizing a small standalone PV system. Before you start, however, it is important to understand the project and customer requirements\n" +
                "and the major steps involved in sizing a PV system. On the next screen, you will see 5 major sizing items (not in the right order). Drag the items and put it in the right box above and click the Ready button once u feel \n" +
                "the order of sizing is correct.");

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent welcomeIntent = new Intent(Welcome.this, Ordering.class);
                startActivity(welcomeIntent);
            }
        });
    }

}

