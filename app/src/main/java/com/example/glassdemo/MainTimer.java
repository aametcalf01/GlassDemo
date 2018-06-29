package com.example.glassdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.security.AccessController.getContext;


public class MainTimer extends Activity {


    private TextView mTime1;
    private TextView mTime2;
    private TextView mTime3;
    private TextView mTime4;
    private TextView mTime5;
    private TextView mTime6;

    private TextView mTaskHeader;
    private TextView mTimer;
    private TextView mInstructions;
    private double startTime;
    private double endTime;
    private String totalTime;

//    Handler handler = new Handler();
//    private Runnable runnableCode = new Runnable() {
//        @Override
//        public void run() {
//            totalTime = getTime(startTime);
//            //String totalTimeStr = String.valueOf(totalTime);
//            mTimer.setText(totalTime);
//            Log.d("Handlers", "Called on main thread");
//            // Repeat this the same runnable code block again another 2 seconds
//            handler.postDelayed(runnableCode, 20);
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mTaskHeader = (TextView) findViewById(R.id.TaskHeader);
        mTimer = (TextView) findViewById(R.id.Timer);
        mInstructions = (TextView) findViewById(R.id.Instructions);

        mTime1 = (TextView) findViewById(R.id.Time1);
        mTime2 = (TextView) findViewById(R.id.Time2);
        mTime3 = (TextView) findViewById(R.id.Time3);
        mTime4 = (TextView) findViewById(R.id.Time4);
        mTime5 = (TextView) findViewById(R.id.Time5);
        mTime6 = (TextView) findViewById(R.id.Time6);

        mTaskHeader.setText("Task 1");
        mInstructions.setText("Tap to begin");
        mTime1.setText("this time");


        setContentView(R.layout.main_timer);

    }


//
//
//    public String getTime(double sTime){
//
//        double eTime = System.currentTimeMillis();
//        double milliseconds = eTime - sTime;
//        String totalTime = String.format("%02d:%02d:%02d",
//                TimeUnit.MILLISECONDS.toHours((long) milliseconds)% 24,
//                TimeUnit.MILLISECONDS.toMinutes((long) milliseconds) % 60,
//                TimeUnit.MILLISECONDS.toSeconds((long) milliseconds)%60);
//        return totalTime;
//    }
}
