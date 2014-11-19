package com.sizing.manthan.sizing;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Manthan on 10/12/2014.
 * This is last activity which is based on rotation of the PV array panel
 */
public class SecondFourth extends Activity {
    ImageView panelIB,poleIV,compassIV;
    TextView AngelTV,HintTextView;
    float clock,anticlock;
    boolean rotated,first,clockY,anticlockY;
    int totalhintsUsed=0,hintCount=0;
    String hints[];
    int log=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondfourth);
        final TextView second2descriptionTV= (TextView) findViewById(R.id.Second2DescriptionTextView);
        TextView second2HeadingTV = (TextView) findViewById(R.id.HeadingSecond2TextView);
        HintTextView= (TextView) findViewById(R.id.HintSecond2TextView);
        second2HeadingTV.setText("Rarotonga Cook Islands, Latitude 12.2 degrees S, Longitude: 159.8 degrees W");
       second2descriptionTV.setText("Determine the tilt and orienation. Click the arrow to adjust tilt and orientation of the solar panel for the critical design month");
        ImageButton downArrowIB= (ImageButton) findViewById(R.id.DownImageButton);
        ImageButton upArrowIB = (ImageButton) findViewById(R.id.UpImageButton);
        ImageButton rotateArrowIB = (ImageButton) findViewById(R.id.RotateImageButton);
        Button hintButton = (Button) findViewById(R.id.Hint2nd2Button);
        hintButton.setOnClickListener(hintButtonListerner);
        Button readyButton = (Button) findViewById(R.id.SecondlevelsecondReadyButton);
        readyButton.setOnClickListener(readyButtonListener);

        panelIB = (ImageView) findViewById(R.id.PanelImageButton);
        AngelTV = (TextView) findViewById(R.id.AngleTextView);
        poleIV= (ImageView) findViewById(R.id.PoleImageView);
        compassIV= (ImageView) findViewById(R.id.CompassImageView);
        compassIV.setImageResource(R.drawable.compassrotated);

        first =true;
        clockY=true;  // y position of the panel when clockwise
        anticlockY=true;//y position of the panel when anticlockwise
        rotated=false; //whether rotated then it is anticlockwise and if not then it is clockwise
        clock=0.0f;
        anticlock=180.0f;

        String hint1 = "In the Southern Hemisphere point the PV Array towars true North and in the Northern Hemisphere towards true South";
        String hint2 = "Best tilt angle for all year is latitude plus 15 degrees";
        hints = new String[]{hint1, hint2};


        downArrowIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(rotated&&anticlock!=90)
                    {
                        anticlock--;
                        anticlockwise(anticlock);
                    }
                    else if(!rotated&&clock!=90)
                    {
                        clock++;
                        clockwise(clock);
                    }
                }
        });

        upArrowIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(rotated&&anticlock!=180)
                    {
                        anticlock++;
                        anticlockwise(anticlock);
                    }
                   else if(!rotated&&clock!=0)
                    {
                        clock--;
                        clockwise(clock);
                    }


            }
        });

        //based on ratation button switches, functions are called
        rotateArrowIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(rotated)
                {
//if anticlockwise then switch to clockwise
                    compassIV.setImageResource(R.drawable.compassrotated);
                    panelIB.setImageResource(R.drawable.panel);
                    View PanelButton = findViewById(R.id.PanelImageButton);
                    RelativeLayout.LayoutParams layoutParams =
                            (RelativeLayout.LayoutParams)PanelButton.getLayoutParams();
                    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                    //layoutParams.leftMargin= (int)poleIV.getX()-panelIB.getWidth()/2-6;
                    // layoutParams.removeRule(RelativeLayout.C);
                    PanelButton.setLayoutParams(layoutParams);
                    rotated=false;
                    Matrix matrix=new Matrix();

                        panelIB.setScaleType(ImageView.ScaleType.MATRIX);   //required
                    clock=180-anticlock; //to maintian the degree on rotation
                        clockwise(clock);
                }
                else
                {
//if clockwise then switch to anticlockwise
                    compassIV.setImageResource(R.drawable.compass);
                    rotated=true;
                    panelIB.setImageResource(R.drawable.panelrotated);
                    DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
                    int width = metrics.widthPixels;
                 //   int height = metrics.heightPixels;

                    View PanelButton = findViewById(R.id.PanelImageButton);
                    RelativeLayout.LayoutParams layoutParams =
                            (RelativeLayout.LayoutParams)PanelButton.getLayoutParams();

                    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, 0);
                    layoutParams.leftMargin= width/2-panelIB.getWidth()/2;

                    PanelButton.setLayoutParams(layoutParams);

                    panelIB.setScaleType(ImageView.ScaleType.MATRIX);   //required
                    anticlock=180-clock;
                    anticlockwise(anticlock);
                }
            }
        });
    }

    //whenever the position is clockwise this function is called
    private void anticlockwise(float x) {
        Matrix matrix=new Matrix();
        panelIB.setScaleType(ImageView.ScaleType.MATRIX);   //required
        matrix.postRotate((float) x, panelIB.getDrawable().getBounds().width()/3,panelIB.getHeight() );
        //matrix.postRotate((float) -1.0, panelIB.getScaleX() / 2, panelIB.getScaleY() / 2); panelIB.getDrawable().getBounds().height()

        panelIB.setImageMatrix(matrix);
        if(anticlockY) {
            panelIB.setY(panelIB.getY() - panelIB.getHeight());
            anticlockY = false;
            clockY=true;
            first =false;
        }
        AngelTV.setText("Angle: " + (180-x));
    }


    //whenever the position is clockwise this function is called
    private void clockwise(float x) {

        Matrix matrix=new Matrix();
        panelIB.setScaleType(ImageView.ScaleType.MATRIX);   //required
        matrix.postRotate((float) x, panelIB.getDrawable().getBounds().width()/3, panelIB.getHeight());
        // matrix.postRotate((float) 1.0, panelIB.getScaleX() / 2, panelIB.getScaleY() / 2);
        if(clockY) {
            if(first)
                panelIB.setY(panelIB.getY() - panelIB.getHeight());
            panelIB.setY(panelIB.getY() + panelIB.getHeight());
          first=false;
            clockY = false;
            anticlockY=true;
        }
        panelIB.setImageMatrix(matrix);

        AngelTV.setText("Angle: " + x);
    }

    private View.OnClickListener hintButtonListerner=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            HintTextView.setText(hints[hintCount]);
            hintCount = ++hintCount % 2;
            totalhintsUsed++;
        }
    };

    //checks whether the position of the panel is correct and performs action accordingly
    private View.OnClickListener readyButtonListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(rotated&&(AngelTV.getText().toString().equals("Angle: 27.0")))
            {
                  LayoutInflater inflater = getLayoutInflater();
                View toastView = inflater.inflate(R.layout.toastview,
                        (ViewGroup) findViewById(R.id.toastLayout));
                TextView toastTV = (TextView) toastView.findViewById(R.id.ToastTextView);
                HintTextView.setText("Congratualtion!! You completed Second level.");
                toastTV.setText("Congratualtion!! You completed Second level.");
                Toast toast = new Toast(SecondFourth.this);
                toast.setView(toastView);
                toast.show();

            }
            else
            {
                String message="Sorry! that is incorrect. "+ hints[hintCount];
                HintTextView.setText(message);
                hintCount=++hintCount%2;
                totalhintsUsed++;
            }
        }
    };

}
