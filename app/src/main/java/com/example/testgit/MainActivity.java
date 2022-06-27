package com.example.testgit;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtRip;
    Button btnBoop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBoop = (Button)findViewById(R.id.btnBoop);
        txtRip = (TextView) findViewById(R.id.txtRip);

        //txtRip.setText("My Awesome Text");
        //Documentation is important^^^

        Toast.makeText(this, "Nothing interesting happens", Toast.LENGTH_LONG).show();

        Socket server = null;
        BufferedReader input = null;
        try {
            server = new Socket("192.168.0.182", 2000);
            input = new BufferedReader(new InputStreamReader(server.getInputStream())); // getting message from client
        }
        catch(Exception e) {
            String errorMessage = e.toString();
            if (errorMessage == null) {
                errorMessage = "Null error message when connecting to or reading from server";
            }
            Log.e("Main", errorMessage);
        }
        try {
            if (input != null && server != null) {
                Log.i("Main", "Message received");
                server.close();

                Toast.makeText(this, input.readLine(),
                        Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e) {
            Log.e("Main", "Uh oh spaghetti oh");
        }
    }

    public void onClick(View view) {
        txtRip = findViewById(R.id.txtRip);
        txtRip.setText("Hello");
    }
}