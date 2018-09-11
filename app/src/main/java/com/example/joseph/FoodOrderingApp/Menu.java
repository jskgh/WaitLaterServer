package com.example.joseph.FoodOrderingApp;

import java.util.ArrayList;

public class Menu {

    String name;
    ArrayList<Item> items;

    public Menu(String name){
        this.name = name;
        this.items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
