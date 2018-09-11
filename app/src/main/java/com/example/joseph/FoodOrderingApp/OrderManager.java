package com.example.joseph.FoodOrderingApp;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderManager {

    private static OrderManager om = null;

    private Order currentOrder;
    private static ArrayList<Order> orderHistory = new ArrayList<>();

    public OrderManager(){
    }

    public static OrderManager getInstance() {
        if (om == null) {
            om = new OrderManager();
        }
        return om;
    }

    public Order getCurrentOrder() {
        if (currentOrder == null) {
            currentOrder = new Order();
        }
        return currentOrder;
    }

    public void confirmOrder(){
        currentOrder.setDate();
        orderHistory.add(currentOrder);
        currentOrder = new Order();
    }

    public void addItem(Item item){
        if(currentOrder != null) {
            currentOrder.addItem(item);
            Log.d("ADD", "Added " + currentOrder.getOrderAsString());
        }
    }

    public void removeItem(Item item){
        if(currentOrder != null) {
            currentOrder.removeItem(item);
            Log.d("REMOVE", "Removed " + currentOrder.getOrderAsString());
        }
    }

    public static ArrayList<Order> getOrderHistory(){
        return orderHistory;
    }


    public String getHistoryAsString(){
        String s = "";
        for (Order o: orderHistory){
            s += o.getOrderAsString() + "\n\n";
        }
        return s;
    }


}
