package com.example.joseph.FoodOrderingApp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<Order>{


    public HistoryAdapter(Context context, ArrayList<Order> orderHistory) {
        super(context, 0, orderHistory);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Order order = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_history, parent, false);
        }

        TextView tvOrderCost = (TextView) convertView.findViewById(R.id.tvOrderCost);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvItems = (TextView) convertView.findViewById(R.id.tvItems);
        TextView tvRestaurant = (TextView) convertView.findViewById(R.id.tvRestaurant);

        tvOrderCost.setText(Double.toString(order.getCost()));
        tvDate.setText(order.getDate());
        tvItems.setText(order.getOrderAsString());
        tvRestaurant.setText(order.getRestaurant());

        return convertView;
    }
}
