package com.example2017.android.webservices;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView textView = (TextView) findViewById(R.id.text);

Runnable runnable=new Runnable() {
    @Override
    public void run() {

        try {
            URL url = new URL("http://developerhendy.16mb.com/hello.php ");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            InputStreamReader streamReader=new InputStreamReader(http.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(streamReader);
           final String text=bufferedReader.readLine();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(text);

                }
            });



        } catch (IOException e) {
            e.printStackTrace();


        }

    }
};
        Thread thread=new Thread(runnable);
        thread.start();


    }}
