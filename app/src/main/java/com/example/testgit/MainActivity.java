package com.example.testgit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtRip;
    Button btnBoop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //txtRip.setText("My Awesome Text");
        btnBoop = (Button)findViewById(R.id.btnBoop);
        txtRip = (TextView) findViewById(R.id.txtRip);
    }


    public void onClick(View view) {
            txtRip = findViewById(R.id.txtRip);
            txtRip.setText("Hello");
    }
}