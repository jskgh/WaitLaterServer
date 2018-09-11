package com.example.joseph.FoodOrderingApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.joseph.FoodOrderingApp.HomeActivity.updateLoginStatus;

public class LoginActivity extends AppCompatActivity {

    public static String venueURL;
    public static String venueName = "UWEDemo";
    public static int tableNoMax = 50;

    HttpGetInfo httpget;

    private Button ipButton;
    private EditText etURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etURL = (EditText) findViewById(R.id.etAddress);
        etURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etURL.setText("");
            }
        });

        etURL.getText();
        ipButton = (Button) findViewById(R.id.getUrlButton);
        ipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                venueURL = etURL.getText().toString();
                String str = "";
                httpget = new HttpGetInfo();

                try {
                    str = (String) httpget.execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Log.d("HTTP",str);
                updateRestaurantInfo(str);
                updateLoginStatus();
            }
        });
    }

    public void updateRestaurantInfo(String str){
        try{
            JSONObject j = new JSONObject(str);
            venueName = j.getString("name");
            tableNoMax = Integer.parseInt(j.getString("maxTableNo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
