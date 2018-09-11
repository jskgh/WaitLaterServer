package com.example.joseph.FoodOrderingApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MenuActivity extends Activity{

    public TextView displayedTotal;
    public static TextView inspectionText;
    public EditText search;
    public Button searchButton;
    HttpGetMenu httpget;
    public static ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        items = new ArrayList<>();
        inspectionText = (TextView) findViewById(R.id.tvDescription);
        displayedTotal = (TextView) findViewById(R.id.tvTotalOrderPrice);
        search = (EditText) findViewById(R.id.etSearch);
        searchButton = (Button) findViewById(R.id.searchButton);


        String str = "";
        httpget = new HttpGetMenu();

        try {
            str = (String) httpget.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //populateMenu(str);

        insertMenu();
        filter(HomeActivity.query);

        MenuAdapter adapter = new MenuAdapter(this, items);
        ListView listView = (ListView) findViewById(R.id.lvFoodItems);
        listView.setAdapter(adapter);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.query = search.getText().toString();
                MenuActivity.super.recreate();
            }
        });


    }


    private Menu insertMenu(){
        Menu m = new Menu("Breakfast");

        String[] allergen1 = {"egg","dairy"};
        String[] allergen2 = {"dairy"};
        items.add(new Item(1,"Full English",5.99,"A full english breakfast with eggs bacon and hashbrowns",allergen1,null));
        items.add(new Item(2,"Syrup Pancakes",4.99,"Syrup pancakes with vanilla ice cream",allergen2,null));
        items.add(new Item(3,"Cheese Omelette",6.99,"Egg omelette with chopped chilli and cheddar cheese",allergen2,null));
        items.add(new Item(4,"BLT Sandwich",4.99,"Bacon lettuce and tomato sandwich in wholemeal bread",allergen1,null));
        items.add(new Item(5,"Scrambled Eggs",3.99,"Scrambled eggs on toast",null,null));

        m.setItems(items);

        return m;
    }


    private void populateMenu(String str) {
        JSONObject j;
        Menu m = null;
        JSONArray jsonArray;
        ArrayList<Item> items = null;
        try {
            j = new JSONObject(str);
            m = new Menu(j.getString("name"));
            jsonArray = j.getJSONArray("items");
            items = new ArrayList<>();
            for(int i = 0; i < jsonArray.length(); i++) {
                String itemString = jsonArray.get(i).toString();
                items.add(convertJSON(itemString));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        m.setItems(items);
        //ArrayList<Item> menuItems = filter();
        //MenuAdapter adapter = new MenuAdapter(this, menuItems);
        //ListView listView = (ListView) findViewById(R.id.lvFoodItems);
        //listView.setAdapter(adapter);

    }

    private void filter(String query){
        if(query!=null) {
            if (!query.equals("")) {
                ArrayList<Item> itemsToKeep = new ArrayList<>();

                for (Item i : items) {
                    if (i.getName().equalsIgnoreCase(query)) {
                        Log.d("SEARCH","Query: " + query + ". Keeping: " + i.getName());
                        itemsToKeep.add(i);
                    }
                }
                items = itemsToKeep;
            }
        }
    }




    private Item convertJSON(String str){

        String substr = str.substring(9,str.length()-2);
        String[] token = substr.split(",");

        int id = 0;
        String name = null;
        String desc = null;
        double cost = 0.00;
        String[] allergens = null;
        String[] tags = null;

        String val = null;
        for (int i = 0; i < token.length; i++){
            int colonIndex = token[i].indexOf(":");
            System.out.println(token[i]);
            String att = token[i].substring(1,colonIndex-1);
            if (att.equals("cost")) {
                val = token[i].substring(colonIndex + 1, token[i].length());
            } else {
                val = token[i].substring(colonIndex + 2, token[i].length() - 1);
            }
            System.out.println(att + "\t\t\t\t\t" + val);
            switch(att){
                case "id":
                    id = Integer.parseInt(val);
                    break;
                case "name":
                    name = val;
                    break;
                case "description":
                    desc = val;
                    break;
                case "cost":
                    cost = Double.parseDouble(val);
                    break;
                case "allergens":
                    allergens = val.split(";");
                    break;
                case "tags":
                    tags = val.split(";");
                    break;
            }
        }

        Item item = new Item(id,name,cost,desc,allergens,tags);

        return item;
    }


}