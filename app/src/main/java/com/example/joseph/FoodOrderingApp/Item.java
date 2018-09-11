package com.example.joseph.FoodOrderingApp;


import java.util.ArrayList;
import java.util.Currency;

public class Item {
    public int id;
    public String name;
    public double cost; // CURRENCY
    public String desc;
    public String[] allergens;
    public String[] tags;

    public Item(int id, String name, double cost, String desc, String[] allergens, String[] tags) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.desc = desc;
        this.allergens = allergens;
        this.tags = tags;
    }

    public String getAllergenString(){
        if (allergens == null || allergens.length == 0)
            return "This item contains: no allergens.";

        StringBuilder sb = new StringBuilder();
        sb.append("This item contains: ");
        for (int i = 0; i < allergens.length; i++) {
            sb.append(String.valueOf(allergens[i]));
            if(i < allergens.length-1){
                sb.append(", ");
            }
        }

        sb.append(".");

        return sb.toString();
    }

    public boolean hasTag(String tag){
        for(int i = 0; i < tags.length; i++){
            if (tag.equalsIgnoreCase(tags[i]));
            return true;
        }

        return false;
    }

    public boolean hasAllergen(String tag){
        if(allergens == null){
            return false;
        }
        for(int i = 0; i < allergens.length; i++){
            if (tag.equalsIgnoreCase(allergens[i]));
            return true;
        }

        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String[] getAllergens() {
        return allergens;
    }

    public void setAllergens(String[] allergens) {
        this.allergens = allergens;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}