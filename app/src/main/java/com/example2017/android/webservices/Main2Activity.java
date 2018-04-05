package com.example2017.android.webservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    String result;
    ArrayList<model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView) findViewById(R.id.view);
        list=new ArrayList<>();



        final Runnable runnable2 = new Runnable() {
            @Override
            public void run() {

                try {

                    URL getdata = new URL("https://elgamal.000webhostapp.com/getdataAsJson%20.php");
                    HttpURLConnection http2 = (HttpURLConnection) getdata.openConnection();
                    InputStreamReader streamReader2 = new InputStreamReader(http2.getInputStream());
                    BufferedReader bufferedReader2 = new BufferedReader(streamReader2);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader2.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    result = stringBuilder.toString();




                    try {
                        JSONObject object = new JSONObject("{"+result+"}");
                        final JSONArray array = object.getJSONArray("posts");


                        for (int i = 0; i < array.length(); i++) {

                            JSONObject current = array.getJSONObject(i);

                            String postwriter = current.getString("postWriter");
                            String postContent = current.getString("postContent");
                            list.add(new model(postwriter, postContent));


                        }


                    } catch (final JSONException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                                Log.e("error is ",e.getMessage());
                                System.out.println(e.getMessage().toString());
                            }
                        });

                    }


                } catch (IOException e) {
                    e.printStackTrace();


                }
            }
        };

        Thread thread2=new Thread(runnable2);
        thread2.start();






        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Adapter moviesAdapter = new Adapter(list);
        recyclerView.setAdapter(moviesAdapter);


    }

    }

