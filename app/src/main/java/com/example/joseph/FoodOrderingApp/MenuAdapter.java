package com.example.joseph.FoodOrderingApp;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MenuAdapter extends ArrayAdapter<Item> {

    public MenuAdapter(Context context, ArrayList<Item> users) {
        super(context, 0, users);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Item item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu, parent, false);
        }

        // INFO BUTTON
        Button infoButton = (Button) convertView.findViewById(R.id.infoButton);
        infoButton.setTag(position);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                Item selectedItem = getItem(position);

                String str = "";

                str = Arrays.toString(selectedItem.allergens);

                MenuActivity.inspectionText.setText(selectedItem.getDesc() + "\n\n" + selectedItem.getAllergenString());
            }
        });


        // ADD BUTTON
        Button addButton = (Button) convertView.findViewById(R.id.addButton);
        // Cache row position inside the button using `setTag`
        addButton.setTag(position);
        // Attach the click event handler
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                // Access the row position here to get the correct data item
                Item selectedItem = getItem(position);

                if(selectedItem != null) {
                    Toast.makeText(getContext(), selectedItem.name + " has been added.", Toast.LENGTH_SHORT).show();
                    OrderManager.getInstance().addItem(selectedItem);
                }
            }
        });




        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvCost = (TextView) convertView.findViewById(R.id.tvPrice);


        // Populate the data into the template view using the data object
        tvName.setText(item.name);
        tvCost.setText(Double.toString(item.cost));
        //MenuActivity.displayedTotal.setText(Double.toString(OrderManager.getInstance().getCurrentOrder().getCost()));

        // Return the completed view to render on screen
        return convertView;
    }

}
