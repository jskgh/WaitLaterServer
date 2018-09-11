package com.example.joseph.FoodOrderingApp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class OrderAdapter extends ArrayAdapter<Item> {

    public OrderAdapter(Context context, ArrayList<Item> orderList) {
        super(context, 0, orderList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Item item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_order, parent, false);
        }


        // Lookup view for data population
        Button clearButton = (Button) convertView.findViewById(R.id.clearButton);
        // Cache row position inside the button using `setTag`
        clearButton.setTag(position);
        // Attach the click event handler
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                // Access the row position here to get the correct data item
                Item selectedItem = getItem(position);

                if(selectedItem != null) {
                    Toast.makeText(getContext(), selectedItem.name + " has been cleared.", Toast.LENGTH_SHORT).show();

                    OrderManager.getInstance().getCurrentOrder().removeItem(selectedItem);
                    OrderAdapter.super.notifyDataSetChanged();
                }
            }
        });





        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvCost = (TextView) convertView.findViewById(R.id.tvPrice);



        // Populate the data into the template view using the data object
        tvName.setText(item.name);
        tvCost.setText(Double.toString(item.cost));
        OrderActivity.displayedTotal.setText("Total: " + Double.toString(OrderManager.getInstance().getCurrentOrder().getCost()));

        // Return the completed view to render on screen
        return convertView;
    }


}