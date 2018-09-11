package com.example.joseph.FoodOrderingApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.joseph.FoodOrderingApp.LoginActivity.venueName;

public class Order {

    private String restaurant;
    private ArrayList<Item> items;
    private double cost = 0.00;
    private String sendDate;
    private String request;

    public Order() {
        this.restaurant = LoginActivity.venueName;
        this.items = new ArrayList<>();
        this.cost = 0.00;
        this.sendDate = "";
        this.request = "";
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public ArrayList<Item> getOrder() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
        updateCost();
    }

    public void removeItem(Item item){
        items.remove(item);
        updateCost();
    }

    public void updateCost() {
        if(items != null) {
            double sum = 0.00;

            if (!items.isEmpty()) {
                for (Item i : items) {
                    sum += i.getCost();
                }
            } else {
                sum = 0.00;
            }

            cost = sum;
        }
    }

    public double getCost() {
        return cost;
    }

    public String getDate() {
        return sendDate;
    }

    public void setDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        sendDate = dateFormat.format(date);
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public void setOrder(ArrayList<Item> items) {
        this.items = items;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getOrderAsString(){

        StringBuilder sb = new StringBuilder();
        for(Item i : items){
            String name = i.getName();
            double cost = i.getCost();
            sb.append(name);
            sb.append("\t");
            sb.append(cost);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void clear(){
        cost = 0.00;
        items.clear();
        sendDate = "";
    }

}
