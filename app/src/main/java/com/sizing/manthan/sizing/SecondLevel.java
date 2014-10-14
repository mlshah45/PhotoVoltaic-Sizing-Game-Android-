package com.sizing.manthan.sizing;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;

/**
 * Created by Manthan on 9/22/2014.
 */
public class SecondLevel extends Activity {
    TextView hintTextView;
    boolean buttonpressed[] = new boolean[4];
    int hintCount, totalhintsUsed;
    String hints[];
    Button springButton, winterButton, fallButton, summerButton;
    ImageView earthImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondthird);

        TextView headingTextView = (TextView) findViewById(R.id.HeadingSecongTextView);
        TextView infoTextView = (TextView) findViewById(R.id.Info2ndTextView);
        hintTextView = (TextView) findViewById(R.id.Hint2ndTextView);
        springButton = (Button) findViewById(R.id.SpringButton);
        winterButton = (Button) findViewById(R.id.WinterButton);
        fallButton = (Button) findViewById(R.id.FallButton);
        summerButton = (Button) findViewById(R.id.SummerButton);
        Button hintButton = (Button) findViewById(R.id.Hint2ndButton);
        Button readyButton = (Button) findViewById(R.id.SecondlevelReadyButton);
        earthImage = (ImageView) findViewById(R.id.EarthImageView);

        springButton.setOnClickListener(ButtonListner);
        fallButton.setOnClickListener(ButtonListner);
        summerButton.setOnClickListener(ButtonListner);
        winterButton.setOnClickListener(ButtonListner);

        headingTextView.setText("Rarotonga Cook Islands, Latitude 12.2 degrees S, Longitude: 159.8 degrees W");
        infoTextView.setText("select the season that includes the critical design month by clicking on one of the for seasons.");

        readyButton.setOnClickListener(readyButtonListener);
        hintButton.setOnClickListener(hintButtonListener);

        hintCount = 0;
        totalhintsUsed = 0;
        String hint1 = "The Earth has a tilt of 23.5 degrees. Because of this, different parts of the Earth are tilted closer to the Sun during diffeerent times of the year. This is why we have seasons.";
        String hint2 = "PSH or peak sun-hours is a measure of the amount of solar isolation being received";
        String hint3 = "The critical design month is usually the month with the lowest solar isolation";
        hints = new String[]{hint1, hint2, hint3};

    }

    protected View.OnClickListener ButtonListner = new View.OnClickListener() {

        public void onClick(View view) {
            Arrays.fill(buttonpressed, false);
            switch (view.getId()) {

                case R.id.SpringButton: {
                    springButton.setBackgroundResource(R.drawable.yspring); //highlighting button pressed
                    earthImage.setImageResource(R.drawable.february);
                    buttonpressed[0] = true;
                    setOtherToOriginalImage(0);
                    break;
                }
                case R.id.WinterButton: {
                    winterButton.setBackgroundResource(R.drawable.ywinter);
                    earthImage.setImageResource(R.drawable.june);
                    buttonpressed[1] = true;
                    setOtherToOriginalImage(1);
                    break;
                }
                case R.id.FallButton: {
                    fallButton.setBackgroundResource(R.drawable.yfall);
                    earthImage.setImageResource(R.drawable.april);
                    buttonpressed[2] = true;
                    setOtherToOriginalImage(2);
                    break;
                }
                case R.id.SummerButton: {
                    summerButton.setBackgroundResource(R.drawable.ysummer);
                    earthImage.setImageResource(R.drawable.december);
                    buttonpressed[3] = true;
                    setOtherToOriginalImage(3);
                    break;
                }
                default:
                    break;
            }
        }
    };

    private void setOtherToOriginalImage(int i) {
        if (i != 0) {
            springButton.setBackgroundResource(R.drawable.bspring);
        }
        if (i != 1) {
            winterButton.setBackgroundResource(R.drawable.bwinter);
        }
        if (i != 2) {
            fallButton.setBackgroundResource(R.drawable.bfall);
        }
        if (i != 3) {
            summerButton.setBackgroundResource(R.drawable.bsummer);
        }
    }


    private View.OnClickListener readyButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (buttonpressed[1]) {
                winterButton.setBackgroundResource(R.drawable.gwinter);
                hintTextView.setText("Congratulations! you selected the season that contains the critical design month.");
                Intent startSecond2 = new Intent(SecondLevel.this,SecondFourth.class);
                startActivity(startSecond2);
              /*  LayoutInflater inflater = getLayoutInflater();
                View toastView = inflater.inflate(R.layout.toastview,
                        (ViewGroup) findViewById(R.id.toastLayout));
                TextView toastTV = (TextView) toastView.findViewById(R.id.ToastTextView);
                toastTV.setText("Congratualtion!! You completed Second level.");
                Toast toast = new Toast(SecondLevel.this);
                toast.setView(toastView);
                toast.show();
                */

            } else if (buttonpressed[0]) {
                springButton.setBackgroundResource(R.drawable.rspring); //making background red color to indicate wrong button select
                hintTextView.setText("The critical design month is not in the spring season");
            } else if (buttonpressed[2]) {
                fallButton.setBackgroundResource(R.drawable.rfall);
                hintTextView.setText("The critical design month is not in the fall season");
            } else if (buttonpressed[3]) {
                summerButton.setBackgroundResource(R.drawable.rsummer);
                hintTextView.setText("The critical design month is not in the summer season");
            }
        }
    };

    private View.OnClickListener hintButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hintTextView.setText(hints[hintCount]);
            hintCount = ++hintCount % 3;
            totalhintsUsed++;
        }
    };
}
