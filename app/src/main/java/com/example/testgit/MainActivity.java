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

    TextView txtRip;
    Button btnBoop;

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

                float curX, curY;
                int mx = 0;
                int my = 0;
                //These are original x and y coordinates

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mx = (int) event.getX();
                        my = (int) event.getY();
                        //x and y cords initialized
                        break;
                    case MotionEvent.ACTION_MOVE:
                        curX = event.getX();
                        curY = event.getY();
                        switcherView.scrollBy((int) (mx - curX), (int) (my - curY));
                        //scrollBy -
                        mx = (int) curX;
                        my = (int) curY;
                        break;
                    case MotionEvent.ACTION_UP:
                        //ACTION_UP - gets current pos when finger is lifted
                        curX = event.getX();
                        curY = event.getY();
                        switcherView.scrollBy((int) (mx - curX), (int) (my - curY));
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