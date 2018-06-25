package com.example.glassdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;


public class TaskTimer extends Activity
        implements GestureDetector.BaseListener, GestureDetector.FingerListener{

    private TextView mTimerHeader;
    private TextView mTimerActions;
    private TextView mTimerDisplay;
    private GestureDetector mGestureDetector;
    private boolean Begin = false;
    private boolean Stop = false;
    private boolean Save = false;
    private double startTime;
    private double endTime;
    private double totalTime;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.timer);

        mTimerHeader = (TextView) findViewById(R.id.TimerHeader);
        mTimerActions = (TextView) findViewById(R.id.TimerActions);
        mTimerDisplay = (TextView) findViewById(R.id.TimerDisplay);

        mTimerHeader.setText("Timer");
        mTimerActions.setText("Tap to begin");

        // Initialize the gesture detector and set the activity to listen to discrete gestures.
        mGestureDetector = new GestureDetector(this).setBaseListener(this).setFingerListener(this);
    }

    /**
     * Overridden to allow the gesture detector to process motion events that occur anywhere within
     * the activity.
     */
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mGestureDetector.onMotionEvent(event);
    }

    @Override
    public boolean onGesture(Gesture gesture){
        if(gesture ==Gesture.TAP && !Begin){
            startTime = System.currentTimeMillis();
            Begin = true;
            mTimerActions.setText("(Tap to stop test)");
            mTimerDisplay.setText("Testing");

            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(200); //You can manage the time of the blink with this parameter
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            mTimerDisplay.startAnimation(anim);
            return true;
        }
        else if(gesture == Gesture.TAP && Begin && !Stop){
            endTime = System.currentTimeMillis();
            totalTime = (endTime-startTime)/1000.0;
            String totalTimeStr = String.valueOf(totalTime);
            Stop = true;
            mTimerDisplay.clearAnimation();
            mTimerActions.setText("(Tap to save result, swipe to start over.)");
            mTimerDisplay.setText(totalTimeStr+" Seconds");
            return true;
        }
        else if(gesture==Gesture.TAP && Begin && Stop && !Save){
            /**
             * TODO: need to add saving here
             */
            Save = true;
            mTimerActions.setText("(Tap for new test)");
            mTimerDisplay.setText("Time saved");
            return true;
        }
        else if (gesture == Gesture.TAP && Begin && Stop && Save){
            startActivity(new Intent(TaskTimer.this, TestSelect.class));
            return true;
        }
        else if (gesture == Gesture.SWIPE_RIGHT && Begin && Stop && !Save){
            startActivity(new Intent(TaskTimer.this, TaskTimer.class));
            return true;
        }
        else{
            return true;
        }
    }
    @Override
    public void onFingerCountChanged(int i, int i1) {

    }
}
