package com.sizing.manthan.sizing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Manthan on 9/17/2014.
 */
public class FirstLevelClear extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstlevelclear);

        TextView timeUsedTextView = (TextView) findViewById(R.id.TimeUsedTextView);
        TextView hintsUsedTextView = (TextView) findViewById(R.id.HintsUsedTextView);
        TextView message1stTextView = (TextView) findViewById(R.id.Messaage1stTextView);
        Button continueButton = (Button) findViewById(R.id.Continue1stButton);

        Intent intent = getIntent();
        Bundle extra = intent.getBundleExtra("extrabundle");

        timeUsedTextView.setText("Time taken: " + extra.getString("time"));
        hintsUsedTextView.setText("Hints Used: " + extra.getString("hints"));
        message1stTextView.setText("You have successfully completed the sizing process. Now get ready to play with each of the sizing steps.Note: you will not need to make an energy assessment");

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openNext = new Intent(FirstLevelClear.this, SecondLevelStart.class);
                startActivity(openNext);

            }
        });


    }
}
