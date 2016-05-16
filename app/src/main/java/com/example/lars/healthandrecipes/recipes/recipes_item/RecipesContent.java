package com.example.lars.healthandrecipes.recipes.recipes_item;

import android.os.AsyncTask;

import com.example.lars.healthandrecipes.connection.RestConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class RecipesContent {

    JSONArray mJsonArray = null;
    JSONObject mJsonObject;
    /**
     * An array of sample (ingredients_item) items.
     */
    public static final List<Recipe> ITEMS = new ArrayList<Recipe>();




    // Add some sample items.
    public RecipesContent() {
        if(ITEMS.isEmpty()){
            EmptyAsync ea = new EmptyAsync();
            ea.execute();
        }else {
            ITEMS.clear();
        }
        System.out.println("jsonarray: " + mJsonArray);
        while(mJsonArray == null){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("jsonarray1: " + mJsonArray);
        for (int i = 0; i < mJsonArray.length(); i++) {

            try {
                mJsonObject = mJsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            addItem(recipeItem(mJsonObject));
        }
    }

    private void addItem(Recipe item) {
        ITEMS.add(item);
    }

    private Recipe recipeItem(JSONObject jsonObject) {
        Recipe recipe = null;
        try {
            recipe = new Recipe(jsonObject.getString("id"), jsonObject.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipe;
    }

    public void setJsonArray(JSONArray jsonArray) {
        mJsonArray = jsonArray;
    }

    /**
     * A Recipe_item item representing a piece of content.
     */
    public class Recipe {
        public final String id;
        public final String name;

        public Recipe(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public class setJsonArray{
        public setJsonArray(JSONArray jsonArray) {
            new RecipesContent().setJsonArray(jsonArray);
        }
    }

    public class EmptyAsync extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {

            RestConnect rc = new RestConnect();
            mJsonArray = rc.getRecipes();

            return null;
        }
    }
}
