package com.example.joseph.FoodOrderingApp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class OrderActivity extends Activity {

    public static TextView displayedTotal;
    public static Button confirmButton;
    public static EditText tableNoInput;
    public static CheckBox barCheckBox;
    public static EditText requestInput;
    HttpSendOrder httpSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        populateOrder();
        httpSend = new HttpSendOrder();

        requestInput = (EditText) findViewById(R.id.requestInput);
        tableNoInput = (EditText) findViewById(R.id.etTableNo);
        barCheckBox = (CheckBox) findViewById(R.id.barCheckBox);

        tableNoInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableNoInput.setText("");
            }
        });

        requestInput.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                requestInput.setText("");
            }
        });

        displayedTotal = (TextView) findViewById(R.id.tvTotalOrderPrice);
        confirmButton = (Button) findViewById(R.id.confirmOrderButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tableNo = 0;
                if(!barCheckBox.isChecked()){
                    try {
                        tableNo = Integer.parseInt(tableNoInput.getText().toString());
                    } catch(NumberFormatException e){

                    }

                    if(tableNo <= LoginActivity.tableNoMax){
                        httpSend.tableNo = tableNo;

                        if(!OrderManager.getInstance().getCurrentOrder().isEmpty()){
                            String req = requestInput.getText().toString();
                            if(req.equals("(Add custom request)")){
                                req = "No Request";
                            }
                            OrderManager.getInstance().getCurrentOrder().setRequest(req);


                            httpSend.execute();
                            Toast.makeText(getApplicationContext(),"Order has been made.", Toast.LENGTH_SHORT).show();
                            OrderManager.getInstance().confirmOrder();
                        } else {
                            Toast.makeText(getApplicationContext(),"Order is Empty", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(),"Table Number Limit is " + LoginActivity.tableNoMax, Toast.LENGTH_SHORT).show();
                    }
                }
        }
        });
    }

    private void populateOrder() {
        ArrayList<Item> listOfItems = OrderManager.getInstance().getCurrentOrder().getItems();
        OrderAdapter adapter = new OrderAdapter(this, listOfItems);
        ListView listView = (ListView) findViewById(R.id.lvOrder);
        listView.setAdapter(adapter);

    }
}
