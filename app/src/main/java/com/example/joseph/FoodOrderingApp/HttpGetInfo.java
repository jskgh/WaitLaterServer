package com.example.joseph.FoodOrderingApp;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpGetInfo extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] params) {

        String response = "";
        HttpClient Client = new DefaultHttpClient();
        String URL = "http://" + LoginActivity.venueURL +":8080/WLApp/info.json";
        Log.d("HTTPERROR",URL);

        try {
            HttpGet httpget = new HttpGet(URL);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = Client.execute(httpget, responseHandler);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }


}
