package com.example.joseph.FoodOrderingApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ArrayList<Order> listOfOrders = OrderManager.getOrderHistory();
        HistoryAdapter adapter = new HistoryAdapter(this, listOfOrders);
        ListView listView = (ListView) findViewById(R.id.lvOrderHistory);
        listView.setAdapter(adapter);
    }
}
