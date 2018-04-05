package com.example2017.android.webservices;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
EditText name,email,password,adress;
    Button button;
    TextView txt;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);

        button=(Button)findViewById(R.id.button);

 txt=(TextView)findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String namee=name.getText().toString();
                String passwordd=password.getText().toString();

                try {
                    url="https://elgamal.000webhostapp.com/setdata.php?"+
                            "username="+ URLEncoder.encode(namee,"UTF-8")
                            +"&password="+ URLEncoder.encode(passwordd,"UTF-8")
                            +"";
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                Runnable runnable=new Runnable() {
    @Override
    public void run() {

        try {
            URL InsertUrl = new URL(url);
            URL getdata=new URL("https://elgamal.000webhostapp.com/getdataAsJson%20.php");

            HttpURLConnection http = (HttpURLConnection) InsertUrl.openConnection();

            HttpURLConnection http2 = (HttpURLConnection) getdata.openConnection();


            InputStreamReader streamReader=new InputStreamReader(http.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(streamReader);




            InputStreamReader streamReader2=new InputStreamReader(http2.getInputStream());
            BufferedReader bufferedReader2=new BufferedReader(streamReader2);


            final String result=bufferedReader.readLine();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                }
            });


           final String result2=bufferedReader2.readLine();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txt.setText(result2);

                }
            });



        } catch (IOException e) {
            e.printStackTrace();



        }

    }
};
        Thread thread=new Thread(runnable);
        thread.start();

            }
        });
    }




    public void show(View v){
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}

