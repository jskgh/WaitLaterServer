package com.example.joseph.FoodOrderingApp;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class HttpSendOrder extends AsyncTask {

    int tableNo;

    @Override
    protected Object doInBackground(Object[] params) {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        Order order = OrderManager.getInstance().getCurrentOrder();
        String request = order.getRequest();
        ArrayList<Item> items = order.getOrder();

        String itemString = "";
        int n = 0;
        for(Item i : items){
            itemString += Integer.toString(i.getId());
            if(n < items.size()-1) {
                itemString += ";";
            }
            n++;
        }

        if(LoginActivity.venueURL != null) {
            String URL = "http://" + LoginActivity.venueURL + ":8080/WLApp/OrderServlet";
            String result = "";
            String msg = tableNo + "'" + itemString + "'" + request;
            HttpPost httpPost = new HttpPost(URL);


            try {
                httpPost.addHeader("content-type", "text/plain");
                HttpEntity entity = new ByteArrayEntity(msg.getBytes("UTF-8"));
                httpPost.setEntity(entity);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                result = httpClient.execute(httpPost, responseHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("Error", httpPost.getRequestLine().toString());
            Log.e("Error", msg);
            Log.e("Error", result);
            return result;
        }

        return "";
    }
}
