package com.example.testgit;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // Log tag
    private final String LOG_TAG = "Main";

    TextView txtRip;
    Button btnBoop;


    private int downX = 0;
    private int downY = 0;
    //Globally set coordinates for onTouch

    @SuppressLint("ClickableViewAccessibility")

    /**
     * This method sets a viewable image that's scrollable with touch input
     * @createMap()
     * @return()
     * Search to get the above to show up properly in dialogue box
     */

    public void createMap() {


        final ImageView switcherView = (ImageView) this.findViewById(R.id.huntMap);

        switcherView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent event) {

                int curX, curY;

                //These are original x and y coordinates

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        downX = (int) event.getX();
                        downY = (int) event.getY();
                        Log.d(LOG_TAG, String.format("ad: downY == %x", downY));
                        Log.d(LOG_TAG, String.format("ad: downX == %x", downX));
                        //x and y cords initialized


                        break;
                    case MotionEvent.ACTION_MOVE:
                        curX = (int) event.getRawX();
                        curY = (int) event.getRawY();

                        switcherView.scrollBy( (downX - curX), (downY - curY));
                        //scrollBy -
                        downX = curX;
                        downY = curY;
                        Log.d(LOG_TAG, String.format("am: downX == %x", downX));
                        Log.d(LOG_TAG, String.format("am: downY == %x", downY));
                       //Scrolling Logs

                        break;
                    case MotionEvent.ACTION_UP:
                        //ACTION_UP - gets current pos when finger is lifted
                        curX = (int) event.getRawX();
                        curY = (int) event.getRawY();
                        switcherView.scrollBy( downX - curX, downY - curY);
                        Log.d(LOG_TAG, String.format("au: downX == %x", downX));
                        Log.d(LOG_TAG, String.format("au: downY == %x", downY));
                        //Final finger position logged
                        break;
                }

                return true;
            }
        });
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createMap();

        btnBoop = (Button)findViewById(R.id.btnBoop);
        txtRip = (TextView) findViewById(R.id.txtRip);


    }


    public void onClick(View view) {

        txtRip = findViewById(R.id.txtRip);
        txtRip.setText("Hello");
    }
}