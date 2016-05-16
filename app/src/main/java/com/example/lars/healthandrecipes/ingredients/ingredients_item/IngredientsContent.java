package com.example.lars.healthandrecipes.ingredients.ingredients_item;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.lars.healthandrecipes.MainActivity;
import com.example.lars.healthandrecipes.R;
import com.example.lars.healthandrecipes.connection.RestConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class IngredientsContent {
    JSONArray jsonArrayRc = null;
    JSONObject jsonObjectRc;

    /**
     * An array of sample (ingredients_item) items.
     */
    public static final List<Ingredient> ITEMS = new ArrayList<Ingredient>();




    // Add some sample items.
    public IngredientsContent() {
        if(!ITEMS.isEmpty()){
            ITEMS.clear();
        }
        RestConnectionAsync rca = new RestConnectionAsync();
        rca.execute();
        System.out.println("jsonarray: " + jsonArrayRc);
        while(jsonArrayRc == null){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("jsonarray1: " + jsonArrayRc);
        for (int i = 0; i < jsonArrayRc.length(); i++) {

            try {
                jsonObjectRc = jsonArrayRc.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            addItem(IngredientItem(jsonObjectRc));
        }
    }

    private void addItem(Ingredient item) {
        ITEMS.add(item);
    }

    private Ingredient IngredientItem(JSONObject jsonObject) {
        Ingredient ingredient = null;
        try {
            ingredient = new Ingredient(jsonObject.getString("id"), jsonObject.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ingredient;
    }

    /**
     * A ingredients_item item representing a piece of content.
     */
    public class Ingredient {
        public final String id;
        public final String name;

        public Ingredient(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public class RestConnectionAsync extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {

            RestConnect rc = new RestConnect();
            jsonArrayRc = rc.getIngredients();

            return null;
        }
    }

}
