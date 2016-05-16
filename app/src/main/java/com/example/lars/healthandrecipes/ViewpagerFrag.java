package com.example.lars.healthandrecipes;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.lars.healthandrecipes.connection.RestConnect;
import com.example.lars.healthandrecipes.ingredients.IngredientFragment;
import com.example.lars.healthandrecipes.ingredients.ingredients_item.IngredientsContent;
import com.example.lars.healthandrecipes.recipes.recipeFragment;
import com.example.lars.healthandrecipes.recipes.recipes_item.RecipesContent;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerFrag extends Fragment {
    private CreateSectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    List<IngredientsContent.Ingredient> mCheckedList = new ArrayList<IngredientsContent.Ingredient>();
    private ViewPager mViewPager;
    recipeFragment rf = new recipeFragment();
    int tempPosition = 0;

    public ViewpagerFrag(List<IngredientsContent.Ingredient> checkedList){
        mCheckedList = checkedList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_viewpager, container, false);

        mSectionsPagerAdapter = new CreateSectionsPagerAdapter(getChildFragmentManager());

        mViewPager = (ViewPager) root.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        PagerSlidingTabStrip tab = (PagerSlidingTabStrip) root.findViewById(R.id.tabs);
        tab.setViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(position == 1 && tempPosition != 1){
                    tempPosition = position;
                    UpdateAsync upAs = new UpdateAsync(mCheckedList);
                    upAs.execute();
                } else if(position == 1 && tempPosition == 1) {
                    //handle weird situations
                } else if(position == 0 && tempPosition != 1) {
                    //handle weird situations
                } else{
                    tempPosition = position;
                        //do asynctask here
                    }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return root;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class CreateSectionsPagerAdapter extends FragmentPagerAdapter {

        public CreateSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return new IngredientFragment();
                case 1:
                    return rf;
                default:
                    return new IngredientFragment();
            }
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ingredienser";
                case 1:
                    return "opskrifter";
            }
            return null;
        }
    }


    public class UpdateAsync extends AsyncTask<Void, Void, Void> {
        List<IngredientsContent.Ingredient> checkedList;

        public UpdateAsync(List<IngredientsContent.Ingredient> checkedList) {
            this.checkedList = checkedList;
        }

        @Override
        protected Void doInBackground(Void... params) {
            String jsonData = new Gson().toJson(checkedList);
            JSONObject jo = null;
            try {
                jo = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RestConnect rc = new RestConnect();
            new RecipesContent().setJsonArray(rc.postWhatCanIMake(jo));

            return null;
        }
    }

}
