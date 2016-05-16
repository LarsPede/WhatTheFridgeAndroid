package com.example.lars.healthandrecipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.lars.healthandrecipes.ingredients.IngredientFragment;
import com.example.lars.healthandrecipes.ingredients.ingredients_item.IngredientsContent;
import com.example.lars.healthandrecipes.recipes.recipeFragment;
import com.example.lars.healthandrecipes.recipes.recipes_item.RecipesContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements recipeFragment.OnListFragmentInteractionListener, IngredientFragment.OnListFragmentInteractionListener {

    List<IngredientsContent.Ingredient> ingredients = new ArrayList<IngredientsContent.Ingredient>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_content, new ViewpagerFrag(getIngredients()))
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onListFragmentInteraction(IngredientsContent.Ingredient item) {
        if(ingredients.contains(item)){
            ingredients.remove(item);
        } else {
            ingredients.add(item);
        }

    }

    public List<IngredientsContent.Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public void onListFragmentInteraction(RecipesContent.Recipe item) {

    }
}
