package com.sizing.manthan.sizing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static android.view.GestureDetector.*;

/**
 * Created by Manthan on 9/5/2014.
 */
public class Ordering extends Activity implements View.OnTouchListener {

    ImageView IVOne, IVTwo, IVThree, IVFour, IVFive, IVOneDrag, IVTwoDrag, IVThreeDrag, IVFourDrag, IVFiveDrag;
    TextView TVResult;
    View viewtapped;
    boolean dragStatus = false;
    double startTime, endTime, noOfHintsUsed;
    boolean isCorrectPosition[];
    GestureDetector gestureDetector;
    int imageResources[] = {R.drawable.energyassessment, R.drawable.criticaldesignmonth, R.drawable.batteries, R.drawable.pvarrays, R.drawable.chargecontroller};
    List<String> selected;
    List<Integer> generated;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordering);
        startTime = System.currentTimeMillis();

        gestureDetector = new GestureDetector(getApplicationContext(), new GestureListener());
        IVOne = (ImageView) findViewById(R.id.IVOne);
        IVTwo = (ImageView) findViewById(R.id.IVTwo);
        IVThree = (ImageView) findViewById(R.id.IVThree);
        IVFour = (ImageView) findViewById(R.id.IVFour);
        IVFive = (ImageView) findViewById(R.id.IVFive);
        TextView TVInstruction = (TextView) findViewById(R.id.InstructionTextView);
        TVResult = (TextView) findViewById(R.id.ResultTextView);

        int imageViews[] = {R.id.IVOneDrag, R.id.IVTwoDrag, R.id.IVThreeDrag, R.id.IVFourDrag, R.id.IVFiveDrag};

        String tags[] = {"OneDrag", "TwoDrag", "ThreeDrag", "FourDrag", "FiveDrag"};
        IVOne.setTag("One");
        IVTwo.setTag("Two");
        IVThree.setTag("Three");
        IVFour.setTag("Four");
        IVFive.setTag("Five");

        //Random image generator
        Random rng = new Random();
        selected = new ArrayList<String>();
        generated = new ArrayList<Integer>();

        //Random image views generator
        for (int i = 0; i < 5; i++) {
            while (true) {
                Integer next = rng.nextInt(5);
                if (!generated.contains(next)) {
                    generated.add(next);

                    ImageView iv = (ImageView) findViewById(imageViews[i]);
                    iv.setImageResource(imageResources[next]);
                    iv.setTag(tags[next]);
                    switch (i) {
                        case 0: {
                            IVOneDrag = iv;
                            break;
                        }
                        case 1: {
                            IVTwoDrag = iv;
                            break;
                        }
                        case 2: {
                            IVThreeDrag = iv;
                            break;
                        }
                        case 3: {
                            IVFourDrag = iv;
                            break;
                        }
                        case 4: {
                            IVFiveDrag = iv;
                            break;
                        }
                        default:
                            break;
                    }
                    break;
                }
            }
        }

        IVOne.setOnTouchListener(this);
        IVTwo.setOnTouchListener(this);
        IVThree.setOnTouchListener(this);
        IVFour.setOnTouchListener(this);
        IVFive.setOnTouchListener(this);
        IVOneDrag.setOnTouchListener(this);
        IVTwoDrag.setOnTouchListener(this);
        IVThreeDrag.setOnTouchListener(this);
        IVFourDrag.setOnTouchListener(this);
        IVFiveDrag.setOnTouchListener(this);

        noOfHintsUsed = 0;
        isCorrectPosition = new boolean[5];

        Button BCheck = (Button) findViewById(R.id.CheckButton);

        TVInstruction.setText("Double tap the item from the lower belt to fit in the position on upper belt in order. Once all items are placed, hit Ready to verify.");
    }

    //checking if items in right order after clicking on ready button
    public void checkOnClick(View v) {

        boolean flag = true, hintUsed = false;
        int indecOfCorrect = -1;
        Arrays.fill(isCorrectPosition, false);

        //checking if the tapped image is in correct position
        if (IVFive.getTag() != null && IVFive.getTag().toString().equals("FiveDrag")) {
            IVFive.setBackgroundColor(0xFF00FF00);  //setting green background
            isCorrectPosition[4] = true;
        } else {
            //if tapped image in wring position
            IVFive.setImageResource(R.drawable.five);
            selected.remove(IVFive.getTag().toString());
            IVFive.setTag("Five");
            flag = false;
            IVFive.setBackgroundColor(0xFFFF0000);  //setting red background
        }


        if (IVFour.getTag() != null && IVFour.getTag().toString().equals("FourDrag")) {
            IVFour.setBackgroundColor(0xFF00FF00);

            isCorrectPosition[3] = true;

        } else {
            IVFour.setImageResource(R.drawable.four);
            selected.remove(IVFour.getTag().toString());
            IVFour.setTag("Four");
            flag = false;
            IVFour.setBackgroundColor(0xFFFF0000); //setting red background
            if (isCorrectPosition[4]) {
                indecOfCorrect = 4;
                hintUsed = true;
                TVResult.setText("Sizing the PV array is done before sizing the controller");
                noOfHintsUsed++;
            }
        }

        if (IVThree.getTag() != null && IVThree.getTag().toString().equals("ThreeDrag")) {
            IVThree.setBackgroundColor(0xFF00FF00);
            isCorrectPosition[2] = true;
        } else {
            selected.remove(IVThree.getTag().toString());
            IVThree.setImageResource(R.drawable.three);
            ;
            IVThree.setTag("Three");
            flag = false;
            IVThree.setBackgroundColor(0xFFFF0000);
            if (isCorrectPosition[3] && hintUsed == false) {
                indecOfCorrect = 4;
                hintUsed = true;
                TVResult.setText("Sizing the battery is done before sizing the PV array");
                noOfHintsUsed++;
            }
        }

        if (IVTwo.getTag() != null && IVTwo.getTag().toString().equals("TwoDrag")) {
            IVTwo.setBackgroundColor(0xFF00FF00);
            isCorrectPosition[1] = true;
        } else {
            selected.remove(IVTwo.getTag().toString());
            IVTwo.setImageResource(R.drawable.two);
            IVTwo.setTag("Two");
            flag = false;
            IVTwo.setBackgroundColor(0xFFFF0000);
            if (isCorrectPosition[2] && hintUsed == false) {
                indecOfCorrect = 4;
                hintUsed = true;
                TVResult.setText("Finding the critical month is done before sizing the battery");
                noOfHintsUsed++;
            }
        }

        if (IVOne.getTag() != null && IVOne.getTag().toString().equals("OneDrag")) {
            IVOne.setBackgroundColor(0xFF00FF00);
            isCorrectPosition[0] = true;
        } else {
            selected.remove(IVOne.getTag().toString());
            IVOne.setImageResource(R.drawable.one);
            IVOne.setTag("One");
            IVOne.setBackgroundColor(0xFFFF0000);
            flag = false;
            if (isCorrectPosition[1] && hintUsed == false) {
                indecOfCorrect = 4;
                hintUsed = true;
                TVResult.setText("Assesing the enrgey requirements is done before sizing the PV array");
                noOfHintsUsed++;
            }
        }

        //if all items are in right order then proceed to next level
        if (flag) {
            endTime = System.currentTimeMillis();
            int totalTimeUsed = (int) ((endTime - startTime) / 1000);

        /*    LayoutInflater inflater = getLayoutInflater();
            View toastView = inflater.inflate(R.layout.toastview,
                    (ViewGroup) findViewById(R.id.toastLayout));
            TextView toastTV = (TextView) toastView.findViewById(R.id.ToastTextView);
            toastTV.setText("Congratualtion!! You completed first level.");

            Toast toast = new Toast(this);
            // toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastView);
            toast.show();*/

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent OpenfirstLevelClear = new Intent(Ordering.this, FirstLevelClear.class);
            Bundle extra = new Bundle();
            extra.putString("time", String.valueOf(totalTimeUsed));
            extra.putString("hints", String.valueOf(noOfHintsUsed));
            OpenfirstLevelClear.putExtra("extrabundle", extra);
            startActivity(OpenfirstLevelClear);
        }
    }

    //getting the image id which is tapped
    private int getImageId(int draggedViewId) {
        if (draggedViewId == R.id.IVFiveDrag)
            return imageResources[generated.get(4)];
        else if (draggedViewId == R.id.IVFourDrag)
            return imageResources[generated.get(3)];
        else if (draggedViewId == R.id.IVThreeDrag)
            return imageResources[generated.get(2)];
        else if (draggedViewId == R.id.IVTwoDrag)
            return imageResources[generated.get(1)];
        else if (draggedViewId == R.id.IVOneDrag)
            return imageResources[generated.get(0)];
        return 0;
    }

    //onn touch calls gesturedetector which detects double tap
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        viewtapped = view;
        return gestureDetector.onTouchEvent(motionEvent);
    }

    private class GestureListener extends SimpleOnGestureListener {

        //removes image on upper strap on touch and displays info when ouched on lower tap
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            String tag;
            if (viewtapped.getId() == R.id.IVOne) {
                selected.remove(viewtapped.getTag().toString());
                IVOne.setImageResource(R.drawable.one);
                IVOne.setTag("One");
                return true;
            } else if (viewtapped.getId() == R.id.IVTwo) {
                selected.remove(viewtapped.getTag().toString());

                IVTwo.setTag("Two");
                IVTwo.setImageResource(R.drawable.two);

                return true;
            } else if (viewtapped.getId() == R.id.IVThree) {
                selected.remove(viewtapped.getTag().toString());

                IVThree.setTag("Three");
                IVThree.setImageResource(R.drawable.three);

                return true;
            } else if (viewtapped.getId() == R.id.IVFour) {
                selected.remove(viewtapped.getTag().toString());

                IVFour.setTag("Four");
                IVFour.setImageResource(R.drawable.four);

                return true;
            } else if (viewtapped.getId() == R.id.IVFive) {
                selected.remove(viewtapped.getTag().toString());

                IVFive.setTag("Five");
                IVFive.setImageResource(R.drawable.five);
                return true;
            } else {
                setResultTextView(viewtapped.getTag().toString());
                return true;
            }

        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
            return false;
        }

        //listens to double tap and copies image above based on the available space
        @Override
        public boolean onDoubleTap(MotionEvent e) {

            if (!selected.contains(viewtapped.getTag().toString())) {
                copyImageAbove();
                selected.add(viewtapped.getTag().toString());
            } else {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                CharSequence text = "Item already selected";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            return true;

        }
    }

    private void setResultTextView(String s) {
        if (s.equals("OneDrag")) {
            TVResult.setText("Access Energy Requirements");
        } else if (s.equals("TwoDrag")) {
            TVResult.setText("find the critical design month");
        } else if (s.equals("ThreeDrag")) {
            TVResult.setText("Size the battery");
        } else if (s.equals("FourDrag")) {
            TVResult.setText("Size The PV Array");
        } else if (s.equals("FiveDrag")) {
            TVResult.setText("Size the controller");
        }
    }


    //Whichever image is double tapped is copied above
    private void copyImageAbove() {
        if (IVOne.getTag().toString().equals("One")) {
            IVOne.setImageResource(getImageId(viewtapped.getId()));
            IVOne.setTag(viewtapped.getTag().toString());
        } else if (IVTwo.getTag().toString().equals("Two")) {
            IVTwo.setImageResource(getImageId(viewtapped.getId()));
            IVTwo.setTag(viewtapped.getTag().toString());
        } else if (IVThree.getTag().toString().equals("Three")) {
            IVThree.setImageResource(getImageId(viewtapped.getId()));
            IVThree.setTag(viewtapped.getTag().toString());
        } else if (IVFour.getTag().toString().equals("Four")) {
            IVFour.setImageResource(getImageId(viewtapped.getId()));
            IVFour.setTag(viewtapped.getTag().toString());
        } else if (IVFive.getTag().toString().equals("Five")) {
            IVFive.setImageResource(getImageId(viewtapped.getId()));
            IVFive.setTag(viewtapped.getTag().toString());
        }
    }
}
